package com.datatablesserverside.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
public class DataTablesServerSide<T> {

    private int draw;

    private int start;

    private long recordsTotal; //필터링 전 전체 레코드 수

    private long recordsFiltered; //필터링 후 전체 레코드 수

    private List<T> data;

    public DataTablesServerSide(int draw, int start, Page<T> data) {
        this.draw = draw;
        this.start = start;
        this.recordsTotal = data.getTotalElements();
        this.recordsFiltered = data.getTotalElements();
        this.data = data.getContent();
    }
}
