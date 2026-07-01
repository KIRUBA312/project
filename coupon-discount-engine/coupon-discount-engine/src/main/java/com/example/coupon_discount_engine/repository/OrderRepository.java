package com.example.coupon_discount_engine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coupon_discount_engine.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	Optional<Order> findByOrderNumber(String orderNumber);
	Optional<Order> findByIdempotencyKey(String idempotencyKey);
	boolean existsByOrderNumber(String orderNumber);
	boolean existsByIdempotencyKey(String idempotencyKey);
	
}
