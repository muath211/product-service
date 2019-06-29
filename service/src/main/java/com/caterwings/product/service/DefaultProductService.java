package com.caterwings.product.service;


import com.caterwings.product.model.dao.ProductRepository;
import com.caterwings.product.model.entity.ProductDocument;
import com.caterwings.product.service.exception.ProductNotFoundException;
import com.caterwings.product.service.mapping.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DefaultProductService extends BaseProductService {

    private ProductRepository productRepository;

    public DefaultProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        super(modelMapper);
        this.productRepository = productRepository;
    }

    @Override
    protected ProductDocument create(ProductDocument productDocument) {
        return productRepository.save(productDocument);
    }

    @Override
    protected ProductDocument findProduct(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product {%s} not found", id)));
    }

    @Override
    protected ProductDocument update(ProductDocument newProductDocument, String id) {
        ProductDocument newProduct = productRepository.findById(id)
                .map(productDocument -> setNewValues(productDocument, newProductDocument))
                .orElseGet(() -> {
                    newProductDocument.setId(id);
                    return newProductDocument;
                });
        return productRepository.save(newProduct);
    }

    private ProductDocument setNewValues(ProductDocument productDocument, ProductDocument newProductDocument) {
        productDocument.setDescription(newProductDocument.getDescription());
        productDocument.setImageUrl(newProductDocument.getImageUrl());
        productDocument.setPrice(newProductDocument.getPrice());
        productDocument.setTitle(newProductDocument.getTitle());
        productDocument.setVendorId(newProductDocument.getVendorId());
        productDocument.setViews(newProductDocument.getViews());
        productDocument.setDietaryFlags(newProductDocument.getDietaryFlags());

        return productDocument;
    }


    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }


}
