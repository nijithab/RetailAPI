package com.retail.retailproduct.repository;


import com.retail.retailproduct.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByProductId(String productId);
}
