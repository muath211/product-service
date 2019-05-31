package com.caterwings.product.service;

import com.caterwings.product.service.dto.Product;

public interface ProductService {

    Product create(Product product);

    Product find(String id);

    Product update(Product product, String id);

    void delete(String id);
}
