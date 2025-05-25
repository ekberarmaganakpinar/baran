package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {

    private Long customerId;

    @OneToMany(mappedBy = "cart")
    private List<Product> products;

    private double totalPrice;
}
