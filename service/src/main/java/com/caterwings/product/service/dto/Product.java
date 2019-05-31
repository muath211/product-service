package com.caterwings.product.service.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Product {

    private String id;

    private String vendorId;

    private String title;

    private String description;

    private Double price;

    private String imageUrl;

    private Long views;

    private Map<String, Boolean> dietaryFlags;


}
