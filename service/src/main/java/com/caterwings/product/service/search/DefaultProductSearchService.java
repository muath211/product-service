package com.caterwings.product.service.search;

import com.caterwings.product.model.dao.ProductRepository;
import com.caterwings.product.model.entity.ProductDocument;
import com.caterwings.product.service.dto.Product;
import com.caterwings.product.service.mapping.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultProductSearchService implements ProductSearchService {

    private ProductRepository productRepository;

    private ModelMapper modelMapper;

    public DefaultProductSearchService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Product> find(Product product) {
        List<ProductDocument> all = productRepository.findAll(Example.of(modelMapper.map(product)));
        return all.stream()
                .map(productDocument -> modelMapper.map(productDocument))
                .collect(Collectors.toList());
    }

}
