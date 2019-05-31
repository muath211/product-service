package com.caterwings.product.service.search;

import com.caterwings.product.service.dto.Product;

import java.util.List;

public interface ProductSearchService {

    List<Product> find(Product product);

}
