package com.ecommerce.repository;

import com.ecommerce.entity.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long> {

    ProductHistory findProductHistoriesByCustomerId(Long customerId);
}
