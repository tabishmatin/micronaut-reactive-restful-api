package com.demo.product.controller;

import com.demo.product.dto.ProductRequestDto;
import com.demo.product.dto.ProductResponseDto;
import com.demo.product.service.ProductService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.inject.Inject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller("/product")
public class DemoProductController {

    @Inject
    private final ProductService productService;

    public DemoProductController(ProductService productService) {
        this.productService = productService;
    }

    @Post
    @Status(HttpStatus.CREATED)
    @Operation(summary = "Create a new product")
    Mono<Object> addProduct(@NonNull @Body ProductRequestDto dto) {
        return productService.addProduct(dto);
    }

    @Get("/{product-id}")
    @Status(HttpStatus.ACCEPTED)
    @Operation(summary = "Get a product by its Product ID")
    Mono<ProductResponseDto> getProductById(@NonNull @PathVariable("product-id") String productId) {
        return productService.getProductById(productId);
    }

    @Get("/name/{product-name}")
    @Status(HttpStatus.ACCEPTED)
    @Operation(summary = "Get a product by its Product Name")
    Mono<ProductResponseDto> getProductByName(@NonNull @PathVariable("product-name") String productName) {
        return productService.getProductByName(productName);
    }

    @Get
    @Status(HttpStatus.ACCEPTED)
    @Operation(summary = "Get all products")
    Flux<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @Put("/{product-id}")
    @Status(HttpStatus.ACCEPTED)
    @Operation(summary = "Update a product by its Product ID")
    Mono<ProductResponseDto> updateProductById(@NonNull @PathVariable("product-id") String productId, @NonNull @Body ProductRequestDto dto) {
        return productService.updateProductById(productId, dto);
    }

    @Delete(value = "/{product-id}")
    @Status(HttpStatus.ACCEPTED)
    @Operation(summary = "Delete a product by its Product ID")
    Mono<String> deleteProductById(@NonNull @PathVariable("product-id") String productId) {
        return productService.deleteProductById(productId);
    }
}