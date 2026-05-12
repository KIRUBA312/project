package com.example.apigateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apigateway.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Long>{

}
