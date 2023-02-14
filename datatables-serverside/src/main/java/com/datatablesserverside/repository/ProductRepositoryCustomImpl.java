package com.datatablesserverside.repository;

import com.datatablesserverside.dto.ProductDto;
import com.datatablesserverside.dto.ProductSearch;
import com.datatablesserverside.dto.QProductDto;
import com.datatablesserverside.entity.QProduct;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QProduct product = QProduct.product;

    @Override
    public Page<ProductDto> getProductList(ProductSearch search) {

        List<ProductDto> list = queryFactory
                .select(
                        new QProductDto(
                                product.id,
                                product.category.name,
                                product.provider.name,
                                product.name,
                                product.price,
                                product.stock,
                                product.createdDate
                        )
                )
                .from(product)
                .orderBy(search.createOrderSpecifier())
                .offset(search.createPageable().getOffset())
                .limit(search.createPageable().getPageSize())
                .fetch();

        Long count = getProductListCount(search);

        return new PageImpl<>(list, search.createPageable(), count);
    }

    private Long getProductListCount(ProductSearch search){
        return queryFactory
                .select(Wildcard.count)
                .from(product)
                .fetchOne();
    }

}
