package com.caterwings.product.service.mapping;

import com.caterwings.product.model.entity.ProductDocument;
import com.caterwings.product.service.dto.Product;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultModelMapper implements ModelMapper {

    @Override
    public ProductDocument map(@NonNull Product product) {
        log.debug("mapping product {}", product);

        ProductDocument productDocument = new ProductDocument();
        productDocument.setVendorId(product.getVendorId());
        productDocument.setTitle(product.getTitle());
        productDocument.setImageUrl(product.getImageUrl());
        productDocument.setPrice(product.getPrice());
        productDocument.setDescription(product.getDescription());
        productDocument.setViews(product.getViews());
        productDocument.setDietaryFlags(product.getDietaryFlags());

        log.debug("product {} mapped to productDocument {}", product, productDocument);
        return productDocument;
    }

    @Override
    public Product map(@NonNull ProductDocument productDocument) {
        log.debug("mapping productDocument {}", productDocument);

        Product product = new Product();
        product.setId(productDocument.getId());

        product.setVendorId(productDocument.getVendorId());
        product.setTitle(productDocument.getTitle());
        product.setPrice(productDocument.getPrice());
        product.setDescription(productDocument.getDescription());
        product.setViews(productDocument.getViews());
        product.setImageUrl(productDocument.getImageUrl());
        product.setDietaryFlags(productDocument.getDietaryFlags());

        log.debug("productDocument with id {} mapped to product {}", productDocument.getId(), product);
        return product;
    }

}
