package com.example.cdc_synchronization_engine.service;

import java.util.List;

import com.example.cdc_synchronization_engine.dto.PaymentRequest;
import com.example.cdc_synchronization_engine.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest request);

    PaymentResponse updatePaymentStatus(
            Long id,
            String status);

    PaymentResponse getPayment(Long id);

    List<PaymentResponse> getAllPayments();
}