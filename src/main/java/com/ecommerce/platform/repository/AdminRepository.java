package com.ecommerce.platform.repository;

import com.ecommerce.platform.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}