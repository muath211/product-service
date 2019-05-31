package com.caterwings.product.service.mapping;

import com.caterwings.product.model.entity.ProductDocument;
import com.caterwings.product.service.dto.Product;


public interface ModelMapper {

    ProductDocument map(Product product);

    Product map(ProductDocument productDocument);

}
