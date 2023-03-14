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
public class ProductRequestDto {

    private String productId;
    private String productName;
    private Float price;
    private Integer stockQuantity;
}
