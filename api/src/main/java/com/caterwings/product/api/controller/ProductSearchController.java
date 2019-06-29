package com.caterwings.product.api.controller;

import com.caterwings.product.api.dto.SearchRequest;
import com.caterwings.product.service.dto.Product;
import com.caterwings.product.service.search.ProductSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products/search")
public class ProductSearchController {

    private ProductSearchService searchService;

    public ProductSearchController(ProductSearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public List<Product> find(@RequestBody SearchRequest searchRequest) {
        log.info("searching Product with attributes {}", searchRequest);

        Product product = createProductWithTitle(searchRequest);
        List<Product> products = searchService.find(product);

        log.debug("Product with attributes {} found {} ", searchRequest, products);
        return products;
    }

    private Product createProductWithTitle(SearchRequest searchRequest) {
        Product product = new Product();
        product.setTitle(searchRequest.getTitle());
        product.setDescription(searchRequest.getDescription());
        return product;
    }


}
