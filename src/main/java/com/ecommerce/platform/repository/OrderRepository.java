package com.ecommerce.platform.repository;

import com.ecommerce.platform.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}