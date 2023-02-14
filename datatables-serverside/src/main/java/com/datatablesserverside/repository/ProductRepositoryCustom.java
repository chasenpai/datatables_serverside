package com.datatablesserverside.repository;

import com.datatablesserverside.dto.ProductDto;
import com.datatablesserverside.dto.ProductSearch;
import org.springframework.data.domain.Page;

public interface ProductRepositoryCustom {

    Page<ProductDto> getProductList(ProductSearch search);

}
