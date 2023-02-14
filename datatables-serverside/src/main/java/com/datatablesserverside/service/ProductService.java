package com.datatablesserverside.service;

import com.datatablesserverside.dto.DataTablesServerSide;
import com.datatablesserverside.dto.ProductDto;
import com.datatablesserverside.dto.ProductSearch;
import com.datatablesserverside.repository.ProductRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepositoryCustom productRepositoryCustom;

    public DataTablesServerSide getProductList(ProductSearch search){
        Page<ProductDto> data = productRepositoryCustom.getProductList(search);
        return new DataTablesServerSide(search.getDraw(), search.getStart(), data);
    }

}
