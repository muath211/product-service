package com.caterwings.product.service;

import com.caterwings.product.model.dao.ProductRepository;
import com.caterwings.product.service.dto.Product;
import com.caterwings.product.service.exception.ProductNotFoundException;
import com.caterwings.product.service.mapping.DefaultModelMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@DataMongoTest
@EnableMongoRepositories(basePackages = "com.caterwings.product.model")
public class DefaultProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    private ProductService defaultProductService;

    @Before
    public void setUp() {
        defaultProductService = new DefaultProductService(productRepository, new DefaultModelMapper());
    }

    @Test
    public void givenSampleProduct_whenCreateCalled_thenItemPersistedToDatabase() {

        Product product = createProduct();
        Product createdProduct = defaultProductService.create(product);

        Assert.assertNotNull(createdProduct);
        Assert.assertEquals(createdProduct.getId(), createdProduct.getId());
        Assert.assertEquals(createdProduct.getVendorId(), product.getVendorId());
        Assert.assertEquals(createdProduct.getDescription(), product.getDescription());
        Assert.assertEquals(createdProduct.getImageUrl(), product.getImageUrl());
        Assert.assertEquals(createdProduct.getPrice(), product.getPrice());
        Assert.assertEquals(createdProduct.getTitle(), product.getTitle());
        Assert.assertNotNull(createdProduct.getDietaryFlags());
        Assert.assertEquals(createdProduct.getDietaryFlags().size(), product.getDietaryFlags().size());
    }

    @Test
    public void givenItemCreated_whenFindCalled_thenReturnItem() {

        Product product = createProduct();
        Product createdProduct = defaultProductService.create(product);

        Product dbProduct = defaultProductService.find(createdProduct.getId());

        Assert.assertNotNull(dbProduct);
        Assert.assertEquals(dbProduct.getId(), createdProduct.getId());
        Assert.assertEquals(dbProduct.getVendorId(), product.getVendorId());
        Assert.assertEquals(dbProduct.getDescription(), product.getDescription());
        Assert.assertEquals(dbProduct.getImageUrl(), product.getImageUrl());
        Assert.assertEquals(dbProduct.getPrice(), product.getPrice());
        Assert.assertEquals(dbProduct.getTitle(), product.getTitle());
        Assert.assertNotNull(dbProduct.getDietaryFlags());
        Assert.assertEquals(dbProduct.getDietaryFlags().size(), product.getDietaryFlags().size());
    }


    @Test
    public void givenItemCreated_whenUpdateCalled_thenItemUpdated() {

        Product product = createProduct();
        Product createdProduct = defaultProductService.create(product);

        String updatedTitle = "updatedTitle";
        createdProduct.setTitle(updatedTitle);
        Product dbProduct = defaultProductService.update(createdProduct, createdProduct.getId());

        Assert.assertNotNull(dbProduct);
        Assert.assertEquals(dbProduct.getId(), createdProduct.getId());
        Assert.assertEquals(dbProduct.getTitle(), updatedTitle);
    }


    @Test(expected = ProductNotFoundException.class)
    public void givenItemCreated_whenDeleteCalled_thenItemDeleted() {

        Product product = createProduct();
        Product createdProduct = defaultProductService.create(product);

        defaultProductService.delete(createdProduct.getId());

        defaultProductService.find(createdProduct.getId());
    }

    private Product createProduct() {
        Product product = new Product();
        product.setVendorId("123");
        product.setImageUrl("url.com/image");
        product.setViews(123L);
        product.setPrice(10.0);
        product.setDescription("itemDescription");
        product.setTitle("itemTitle");
        HashMap<String, Boolean> dietaryFlags = new HashMap<>();
        dietaryFlags.put("sample", true);
        product.setDietaryFlags(dietaryFlags);
        return product;
    }
}