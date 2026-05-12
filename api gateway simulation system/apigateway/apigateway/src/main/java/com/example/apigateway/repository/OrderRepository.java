package com.example.apigateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apigateway.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

}
