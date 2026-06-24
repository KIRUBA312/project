package com.example.enterprise_order_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enterprise_order_system.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

	List<Payment> findByOrderId(Long orderId);
	boolean existsByTransactionId(String transactionId);
	
}
