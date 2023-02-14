package com.datatablesserverside.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class ProductDto {

    private long id;

    private String category;

    private String provider;

    private String name;

    private int price;

    private int stock;

    private String createdDate;

    @QueryProjection
    public ProductDto(Long id, String category, String provider, String name, Integer price, Integer stock, LocalDateTime createdDate) {
        this.id = id;
        this.category = category;
        this.provider = provider;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
