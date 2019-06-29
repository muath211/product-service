package com.caterwings.product.service.search;

import com.caterwings.product.model.dao.ProductRepository;
import com.caterwings.product.service.DefaultProductService;
import com.caterwings.product.service.ProductService;
import com.caterwings.product.service.dto.Product;
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
import java.util.List;


@RunWith(SpringRunner.class)
@DataMongoTest
@EnableMongoRepositories(basePackages = "com.caterwings.product.model")
public class DefaultProductSearchServiceTest {

    @Autowired
    private ProductRepository productRepository;

    private ProductService defaultProductService;

    private ProductSearchService productSearchService;

    @Before
    public void setUp() {
        DefaultModelMapper modelMapper = new DefaultModelMapper();
        defaultProductService = new DefaultProductService(productRepository, modelMapper);
        productSearchService = new DefaultProductSearchService(productRepository, modelMapper);
    }

    @Test
    public void givenNoProductsExists_whenSearchByTitleCalled_thenReturnEmptyResult() {

        Product searchExample = new Product();
        searchExample.setTitle("title");

        List<Product> products = productSearchService.find(searchExample);

        Assert.assertNotNull(products);
        Assert.assertEquals(0, products.size());
    }

    @Test
    public void givenMultipleProductExist_whenSearchByTitleCalled_thenReturnExistingProducts() {
        Product product = createProductWithTitle("itemTitle");
        Product product2 = createProductWithTitle("itemTitle2");
        defaultProductService.create(product);
        defaultProductService.create(product);
        defaultProductService.create(product);
        defaultProductService.create(product2);

        Product searchExample = new Product();
        searchExample.setTitle(product.getTitle());

        List<Product> products = productSearchService.find(searchExample);

        Assert.assertNotNull(products);
        Assert.assertEquals(3, products.size());
    }

    @Test
    public void givenNoProductsExists_whenSearchByDescriptionCalled_thenReturnEmptyResult() {

        Product searchExample = new Product();
        searchExample.setDescription("description");

        List<Product> products = productSearchService.find(searchExample);

        Assert.assertNotNull(products);
        Assert.assertEquals(0, products.size());
    }

    @Test
    public void givenMultipleProductExist_whenSearchByDescriptionCalled_thenReturnExistingProducts() {
        Product product = createProductWithDescription("description");
        Product product2 = createProductWithDescription("description2");
        defaultProductService.create(product);
        defaultProductService.create(product);
        defaultProductService.create(product);
        defaultProductService.create(product2);

        Product searchExample = new Product();
        searchExample.setDescription(product.getDescription());

        List<Product> products = productSearchService.find(searchExample);

        Assert.assertNotNull(products);
        Assert.assertEquals(3, products.size());
    }

    private Product createProductWithTitle(String itemTitle) {
        return getProduct(itemTitle, "itemDescription");
    }

    private Product createProductWithDescription(String description) {
        return getProduct("title", description);
    }

    private Product getProduct(String itemTitle, String itemDescription) {
        Product product = new Product();
        product.setVendorId("123");
        product.setImageUrl("url.com/image");
        product.setViews(123L);
        product.setPrice(10.0);
        product.setDescription(itemDescription);
        product.setTitle(itemTitle);
        HashMap<String, Boolean> dietaryFlags = new HashMap<>();
        dietaryFlags.put("sample", true);
        product.setDietaryFlags(dietaryFlags);
        return product;
    }

}