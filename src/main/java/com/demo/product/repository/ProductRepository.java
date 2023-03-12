package com.demo.product.repository;

import com.demo.product.entity.Product;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;

@MongoRepository
public interface ProductRepository extends ReactiveStreamsCrudRepository<Product, String> {
}
