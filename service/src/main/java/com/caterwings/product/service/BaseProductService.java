package com.caterwings.product.service;

import com.caterwings.product.model.entity.ProductDocument;
import com.caterwings.product.service.dto.Product;
import com.caterwings.product.service.mapping.ModelMapper;
import lombok.NonNull;

public abstract class BaseProductService implements ProductService {

    private ModelMapper modelMapper;

    protected BaseProductService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Product create(@NonNull Product product) {
        return modelMapper.map(create(modelMapper.map(product)));
    }

    protected abstract ProductDocument create(ProductDocument product);

    @Override
    public Product find(@NonNull String id) {
        return modelMapper.map(findProduct(id));
    }

    protected abstract ProductDocument findProduct(String id);

    @Override
    public Product update(@NonNull Product product, @NonNull String id) {
        return modelMapper.map(update(modelMapper.map(product), id));
    }

    protected abstract ProductDocument update(ProductDocument product, String id);


}
