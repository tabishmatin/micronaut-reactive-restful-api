package com.demo.product.entity;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.*;
import org.bson.types.ObjectId;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@MappedEntity("Product")
public class Product {

    @Id
    private ObjectId id;

    private String productId;
    private String productName;
    private Float price;
    private Integer stockQuantity;
    private String createdAt;
    private String modifiedAt;
}
