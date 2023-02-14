package com.datatablesserverside.controller;

import com.datatablesserverside.dto.DataTablesServerSide;
import com.datatablesserverside.dto.ProductSearch;
import com.datatablesserverside.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/list")
    public String getProductList(ProductSearch search){
        return "index";
    }

    @PostMapping("/product/list-data")
    @ResponseBody
    public DataTablesServerSide getProductListData(ProductSearch search){
        return productService.getProductList(search);
    }

}
