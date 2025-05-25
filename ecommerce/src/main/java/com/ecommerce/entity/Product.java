package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    private String name;

    private String description;

    private double price;

    private int stock;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
