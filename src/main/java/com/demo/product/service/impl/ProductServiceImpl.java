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
        return productRepository.findByProductId(productId)
                .map(product -> ProductResponseDto.builder()
                        .productId(productId)
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .stockQuantity(product.getStockQuantity())
                        .createdAt(product.getCreatedAt())
                        .modifiedAt(product.getModifiedAt())
                        .build());
    }

    @Override
    public Mono<ProductResponseDto> getProductByName(String productName) {
        return productRepository.findByProductName(productName)
                .map(product -> ProductResponseDto.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .stockQuantity(product.getStockQuantity())
                        .createdAt(product.getCreatedAt())
                        .modifiedAt(product.getModifiedAt())
                        .build());
    }

    @Override
    public Flux<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .map(product -> ProductResponseDto.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .stockQuantity(product.getStockQuantity())
                        .createdAt(product.getCreatedAt())
                        .modifiedAt(product.getModifiedAt())
                        .build());
    }

    @Override
    public Mono<ProductResponseDto> updateProductById(String productId, ProductRequestDto dto) {
        return productRepository.findByProductId(productId)
                .switchIfEmpty(Mono.error(new HttpStatusException(HttpStatus.NOT_FOUND, "Product with the given ID does not exist")))
                .flatMap(product -> productRepository.update(Product.builder()
                                .productName(dto.getProductName() == null ? product.getProductName() : dto.getProductName())
                                .price(dto.getPrice() == null ? product.getPrice() : dto.getPrice())
                                .stockQuantity(dto.getStockQuantity() == null ? product.getStockQuantity() : dto.getStockQuantity())
                                .createdAt(product.getCreatedAt())
                                .modifiedAt(currDateAsString())
                                .build())
                        .map(savedProduct -> ProductResponseDto.builder()
                                .productId(savedProduct.getProductId())
                                .productName(savedProduct.getProductName())
                                .price(savedProduct.getPrice())
                                .stockQuantity(savedProduct.getStockQuantity())
                                .createdAt(savedProduct.getCreatedAt())
                                .modifiedAt(savedProduct.getModifiedAt())
                                .build()));
    }

    @Override
    public Mono<String> deleteProductById(String productId) {
        return productRepository.deleteByProductId(productId)
                .flatMap(deleted -> Mono.just("Product with Product ID " + productId + " deleted successfully."))
                .switchIfEmpty(Mono.error(new HttpStatusException(HttpStatus.NOT_FOUND, "Product with the given ID does not exist")));
    }
}
