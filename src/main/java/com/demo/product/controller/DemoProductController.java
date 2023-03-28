package com.demo.product.controller;

import com.demo.product.dto.ProductRequestDto;
import com.demo.product.dto.ProductResponseDto;
import com.demo.product.service.ProductService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import reactor.core.publisher.Mono;

@Controller("/demoProduct")
public class DemoProductController {

    @Inject
    private final ProductService productService;

    public DemoProductController(ProductService productService) {
        this.productService = productService;
    }

    @Post
    @Status(HttpStatus.CREATED)
    Mono<Object> addProduct(@NonNull @Body ProductRequestDto dto) {
        return productService.addProduct(dto);
    }

    @Get("/{product-id}")
    @Status(HttpStatus.ACCEPTED)
    Mono<ProductResponseDto> getProductById(@NonNull @PathVariable("product-id") String productId) {
        return productService.getProductById(productId);
    }
}