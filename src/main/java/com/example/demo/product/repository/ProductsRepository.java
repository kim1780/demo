package com.example.demo.product.repository;

import com.example.demo.product.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository
        extends JpaRepository<ProductsEntity, Long> {
}