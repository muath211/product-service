package com.caterwings.product.service.mapping;

import com.caterwings.product.model.entity.ProductDocument;
import com.caterwings.product.service.dto.Product;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DefaultModelMapperTest {

    DefaultModelMapper defaultModelMapper = new DefaultModelMapper();

    @Test(expected = NullPointerException.class)
    public void givenNullProduct_whenMapperCalled_thenExceptionWillBeThrown() {
        Product product = null;
        defaultModelMapper.map(product);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullProductDocument_whenMapperCalled_thenExceptionWillBeThrown() {
        Product product = null;
        defaultModelMapper.map(product);
    }

    @Test
    public void givenFilledProduct_whenMapperCalled_thenReturnProductDocument() {

        Product product = new Product();
        product.setId("id");
        product.setTitle("title");
        product.setDescription("description");
        product.setVendorId("vendorId");
        product.setPrice(10.0);
        product.setViews(100L);
        product.setImageUrl("url.com/image");
        HashMap<String, Boolean> dietaryFlags = new HashMap<>();
        dietaryFlags.put("sample", true);
        product.setDietaryFlags(dietaryFlags);

        ProductDocument productDocument = defaultModelMapper.map(product);

        assertNotNull(productDocument.getTitle());
        assertNotNull(productDocument.getDescription());
        assertNotNull(productDocument.getVendorId());
        assertNotNull(productDocument.getPrice());
        assertNotNull(productDocument.getViews());
        assertNotNull(productDocument.getImageUrl());
        assertNotNull(productDocument.getDietaryFlags());
        assertEquals(productDocument.getDietaryFlags().size(), 1);
    }

    @Test
    public void givenFilledProductDocument_whenMapperCalled_thenReturnProduct() {

        ProductDocument productDocument = new ProductDocument();
        productDocument.setId("id");
        productDocument.setTitle("title");
        productDocument.setDescription("description");
        productDocument.setVendorId("vendorId");
        productDocument.setPrice(10.0);
        productDocument.setViews(100L);
        productDocument.setImageUrl("url.com/image");
        HashMap<String, Boolean> dietaryFlags = new HashMap<>();
        dietaryFlags.put("sample", true);
        productDocument.setDietaryFlags(dietaryFlags);

        Product product = defaultModelMapper.map(productDocument);

        assertNotNull(product.getId());
        assertNotNull(product.getTitle());
        assertNotNull(product.getDescription());
        assertNotNull(product.getVendorId());
        assertNotNull(product.getPrice());
        assertNotNull(product.getViews());
        assertNotNull(product.getImageUrl());
        assertNotNull(product.getDietaryFlags());
        assertEquals(product.getDietaryFlags().size(), 1);
    }
}