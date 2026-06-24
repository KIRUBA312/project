package com.example.enterprise_order_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enterprise_order_system.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	Optional<Order> findByIdempotencyKey(String idempotencyKey);
	boolean existsByIdempotencyKey(String idempotencyKey);
	
}
