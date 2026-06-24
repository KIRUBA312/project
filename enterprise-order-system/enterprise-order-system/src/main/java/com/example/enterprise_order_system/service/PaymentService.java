package com.example.enterprise_order_system.service;

import java.util.List;

import com.example.enterprise_order_system.dto.PaymentRequestDto;
import com.example.enterprise_order_system.dto.PaymentResponseDto;

import jakarta.validation.Valid;

public interface PaymentService {

	PaymentResponseDto processPayment(@Valid PaymentRequestDto request);

	List<PaymentResponseDto> getAllPayments();

	PaymentResponseDto getPaymentsById(Long id);

	void deletePayment(Long id);

	PaymentResponseDto updatePayment(Long id, PaymentRequestDto request);

}
