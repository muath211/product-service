package com.caterwings.product.api.controller;

import com.caterwings.product.service.ProductService;
import com.caterwings.product.service.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) {
        log.info("creating Product {}", product);
        Product createdProduct = productService.create(product);
        log.info("Product with id {} has been successfully created", createdProduct.getId());
        return createdProduct;
    }


    @GetMapping("/{id}")
    public Product findProduct(@PathVariable String id) {
        log.info("finding Product with id {}", id);
        Product product = productService.find(id);
        log.info("product with id {} found with value {}", id, product);
        return product;
    }


    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable String id) {
        log.info("updating product {} with id {}", product, id);
        Product updatedProduct = productService.update(product, id);
        log.info("product with id {} has been updated with value {}", id, updatedProduct);
        return updatedProduct;
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        log.info("deleting product with id {}", id);
        productService.delete(id);
        log.info("product with id {} has been deleted", id);
    }


}
