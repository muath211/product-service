package com.caterwings.product.model.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Document(collection = "product")
public class ProductDocument {

    @Id
    private String id;

    private String vendorId;

    @Indexed
    private String title;

    @Indexed
    private String description;

    private Double price;

    private String imageUrl;

    private Map<String, Boolean> dietaryFlags;

    private Long views;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;


}
