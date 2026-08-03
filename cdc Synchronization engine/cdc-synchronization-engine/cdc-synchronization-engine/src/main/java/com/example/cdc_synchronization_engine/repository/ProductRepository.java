package com.example.cdc_synchronization_engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.Product;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    boolean existsByProductCode(String productCode);

    boolean existsByProductCodeAndIdNot(
            String productCode,
            Long id);
}