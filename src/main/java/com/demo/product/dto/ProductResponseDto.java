package com.demo.product.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Introspected
public class ProductResponseDto {

    private String productId;
    private String productName;
    private Float price;
    private Integer stockQuantity;
    private String createdAt;
    private String modifiedAt;
}
