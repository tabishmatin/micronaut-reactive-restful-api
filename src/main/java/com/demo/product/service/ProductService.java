package com.demo.product.service;

import com.demo.product.dto.ProductRequestDto;
import com.demo.product.dto.ProductResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<ProductResponseDto> addProduct(ProductRequestDto productRequestDto);
    Mono<ProductResponseDto> getProductById(String productId);
    Mono<ProductResponseDto> getProductByName(String productName);
    Flux<ProductResponseDto> getAllProducts();
    Mono<ProductResponseDto> updateProductById(String productId);
    Mono<String> deleteProductById(String productId);

}