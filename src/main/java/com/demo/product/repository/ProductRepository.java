package com.demo.product.repository;

import com.demo.product.entity.Product;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

@MongoRepository
public interface ProductRepository extends ReactorCrudRepository<Product, ObjectId> {

    Mono<Product> findByProductName(String productName);
    Mono<Product> findByProductId(String productId);
}
