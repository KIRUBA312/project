package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.PaymentRequest;
import com.example.cdc_synchronization_engine.dto.PaymentResponse;
import com.example.cdc_synchronization_engine.entity.Payment;

@Component
public class PaymentMapper {

    public Payment toEntity(PaymentRequest request){

        Payment payment=new Payment();

        payment.setPaymentReference(request.getPaymentReference());
        payment.setOrderId(request.getOrderId());
        payment.setPaymentAmount(request.getPaymentAmount());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPaymentStatus("PENDING");

        return payment;
    }

    public PaymentResponse toResponse(Payment payment){

        PaymentResponse response=new PaymentResponse();

        response.setId(payment.getId());
        response.setPaymentReference(payment.getPaymentReference());
        response.setOrderId(payment.getOrderId());
        response.setPaymentAmount(payment.getPaymentAmount());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setPaymentStatus(payment.getPaymentStatus());
        response.setCreatedAt(payment.getCreatedAt());

        return response;
    }
}