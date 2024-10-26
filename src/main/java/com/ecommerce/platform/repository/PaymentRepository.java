package com.ecommerce.platform.repository;

import com.ecommerce.platform.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}