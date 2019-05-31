package com.caterwings.product.model.dao;

import com.caterwings.product.model.entity.ProductDocument;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<ProductDocument, String> {

    Optional<ProductDocument> findById(String id);

    void deleteById(String id);

}
