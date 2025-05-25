package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPriceHistoryDto {
    private Long productId;
    private String productName;
    private double oldPrice;
    private double newPrice;
}
