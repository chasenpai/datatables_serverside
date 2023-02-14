package com.datatablesserverside.dto;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.thymeleaf.util.StringUtils;

@Data
public class ProductSearch {

    private Integer draw; //데이터 갱신 횟수

    private Integer start; //갱신할 데이터의 시작 인덱스

    private Integer length; //한 번에 가져올 데이터 개수

    private String sortColumn; //정렬 기준 컬럼

    private String sortDirection; //정렬 방향

    private String sortTable; //조인 시 정렬 기준 테이블

    /**
     * Pageable 객체 생성
     */
    public Pageable createPageable(){

        int pageNum = this.start/this.length;
        Sort.Direction sortDirection = setSortDirection();
        Sort sort = Sort.by(sortDirection, this.sortColumn);

        return PageRequest.of(pageNum, this.length, sort);
    }

    private Sort.Direction setSortDirection(){

        if(StringUtils.equals("1", this.sortDirection) || StringUtils.equalsIgnoreCase("asc", this.sortDirection)) {
            return Sort.Direction.ASC;
        }
        return Sort.Direction.DESC;
    }

    /**
     * Pageable Sort > Querydsl OrderSpecifier 변환
     */
    public OrderSpecifier createOrderSpecifier(){

        StringPath stringPath = Expressions.stringPath(this.sortColumn);
        Order orderDirection = setOrderDirection();

        return new OrderSpecifier<>(orderDirection, stringPath);
    }

    private Order setOrderDirection(){

        if(StringUtils.equals("1", this.sortDirection) || StringUtils.equalsIgnoreCase("asc", this.sortDirection)) {
            return Order.ASC;
        }
        return Order.DESC;
    }

    public OrderSpecifier createOrderSpecifierUseJoin(){

        StringPath stringPath = Expressions.stringPath(this.sortTable + "." + this.sortColumn);
        Order orderDirection = setOrderDirection();

        return new OrderSpecifier<>(orderDirection, stringPath);
    }

}
