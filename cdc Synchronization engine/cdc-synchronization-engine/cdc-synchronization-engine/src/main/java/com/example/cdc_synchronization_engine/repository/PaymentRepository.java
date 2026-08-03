package com.example.cdc_synchronization_engine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.Payment;

@Repository
public interface PaymentRepository
        extends JpaRepository<Payment, Long> {

    Optional<Payment> findByPaymentReference(
            String paymentReference);

    boolean existsByPaymentReference(String paymentReference);

    List<Payment> findByOrderId(Long orderId);

    boolean existsByOrderId(Long orderId);
}