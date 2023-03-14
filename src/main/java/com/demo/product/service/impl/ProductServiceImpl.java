package com.demo.product.service.impl;

import com.demo.product.dto.ProductRequestDto;
import com.demo.product.dto.ProductResponseDto;
import com.demo.product.service.ProductService;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Singleton
public class ProductServiceImpl implements ProductService {

    @Override
    public Mono<ProductResponseDto> addProduct(ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public Mono<ProductResponseDto> getProductById(String productId) {
        return null;
    }

    @Override
    public Mono<ProductResponseDto> getProductByName(String productName) {
        return null;
    }

    @Override
    public Flux<ProductResponseDto> getAllProducts() {
        return null;
    }

    @Override
    public Mono<ProductResponseDto> updateProductById(String productId) {
        return null;
    }

    @Override
    public Mono<String> deleteProductById(String productId) {
        return null;
    }
}
