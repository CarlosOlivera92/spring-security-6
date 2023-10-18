package com.charlesxvr.springsecurity6.repository;

import com.charlesxvr.springsecurity6.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
