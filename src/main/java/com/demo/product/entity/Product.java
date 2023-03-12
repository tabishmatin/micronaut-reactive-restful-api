package com.demo.product.entity;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@MappedEntity("product")
public class Product {

    @Id
    private String id;

    private String productId;
    private String productName;
    private String productDesc;
    private Float price;
    private Integer stockQuantity;
    private String createdAt;
    private String modifiedAt;
}
