package com.demo.product.service.impl;

import com.demo.product.dto.ProductRequestDto;
import com.demo.product.dto.ProductResponseDto;
import com.demo.product.entity.Product;
import com.demo.product.repository.ProductRepository;
import com.demo.product.service.ProductService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Singleton
public class ProductServiceImpl implements ProductService {

    @Inject
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private String currDateAsString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss"));
    }

    @Override
    public Mono<Object> addProduct(ProductRequestDto productRequestDto) {
        return productRepository.findByProductName(productRequestDto.getProductName().toUpperCase())
                .flatMap(product -> Mono.error(new HttpStatusException(HttpStatus.CONFLICT, "Product with the same name already exists")))
                .switchIfEmpty(Mono.defer(() -> {
                    String productId = UUID.randomUUID().toString();
                    Product product = Product.builder()
                            .productId(productId)
                            .productName(productRequestDto.getProductName().toUpperCase())
                            .price(productRequestDto.getPrice())
                            .stockQuantity(productRequestDto.getStockQuantity())
                            .createdAt(currDateAsString())
                            .modifiedAt(currDateAsString())
                            .build();
                    return productRepository.save(product)
                            .map(savedProduct -> ProductResponseDto.builder()
                                    .productId(productId)
                                    .productName(savedProduct.getProductName())
                                    .price(savedProduct.getPrice())
                                    .stockQuantity(savedProduct.getStockQuantity())
                                    .createdAt(savedProduct.getCreatedAt())
                                    .modifiedAt(savedProduct.getModifiedAt())
                                    .build());
                }));
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
