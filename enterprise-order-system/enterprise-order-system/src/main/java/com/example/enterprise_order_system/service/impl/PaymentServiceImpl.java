package com.example.enterprise_order_system.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprise_order_system.common.constants.KafkaTopics;
import com.example.enterprise_order_system.common.constants.PaymentStatus;
import com.example.enterprise_order_system.dto.PaymentRequestDto;
import com.example.enterprise_order_system.dto.PaymentResponseDto;
import com.example.enterprise_order_system.entity.Payment;
import com.example.enterprise_order_system.exception.PaymentFailedException;
import com.example.enterprise_order_system.exception.ResourceNotFoundException;
import com.example.enterprise_order_system.repository.PaymentRepository;
import com.example.enterprise_order_system.service.KafkaProducerService;
import com.example.enterprise_order_system.service.PaymentService;
import com.example.enterprise_order_system.service.SagaService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private KafkaProducerService kafkaProducerService;
	@Autowired
	private SagaService sagaService;
	@Override
	@Retry(
			name = "paymentRetry",
			fallbackMethod = "paymentFallback")
	@CircuitBreaker(
			name = "paymentCircuitBreaker",
			fallbackMethod = "paymentFallback")
	public PaymentResponseDto processPayment(@Valid PaymentRequestDto request) {
		// TODO Auto-generated method stub
		Payment payment = new Payment();
		payment.setOrderId(request.getOrderId());
		payment.setAmount(request.getAmount());
		payment.setStatus(PaymentStatus.SUCCESS.name());
		payment.setTransactionId(UUID.randomUUID()
				.toString());
		payment.setCreatedAt(LocalDateTime.now());
		Payment savedPayment = paymentRepository.save(payment);
		kafkaProducerService.publishMessage(KafkaTopics.PAYMENT_SUCCESS,
				"Payment Success : "+savedPayment.getId());
		sagaService.handlePaymentSuccess(request.getOrderId());
		return maptoresponse(savedPayment);
	}
	public PaymentResponseDto paymentFallback(
			PaymentRequestDto request, Exception ex) {
		sagaService.handlePaymentFailure(request.getOrderId());
		kafkaProducerService.publishMessage(KafkaTopics.PAYMENT_FAILED,
				"Payment Failed");
		throw new PaymentFailedException("Payment Service Temporarily Unavilable");
	}
	private PaymentResponseDto maptoresponse(Payment payment) {
		// TODO Auto-generated method stub
		PaymentResponseDto response = new PaymentResponseDto();
		response.setPaymentId(payment.getId());
		response.setStatus(payment.getStatus());
		return response;
	}
	@Override
	public List<PaymentResponseDto> getAllPayments() {
		// TODO Auto-generated method stub
		return paymentRepository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	@Override
	public PaymentResponseDto getPaymentsById(Long id) {
		// TODO Auto-generated method stub
		Payment payment = paymentRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Payment not found with id : "+id));
		
		return maptoresponse(payment);
	}
	@Override
	public void deletePayment(Long id) {
		// TODO Auto-generated method stub
		Payment payment = paymentRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("payment not found"));
		paymentRepository.delete(payment);
		
	}
	@Override
	public PaymentResponseDto updatePayment(Long id, PaymentRequestDto request) {
		// TODO Auto-generated method stub
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Payment not found"));
		payment.setAmount(request.getAmount());
		payment.setOrderId(request.getOrderId());
		Payment updatedPayment = paymentRepository.save(payment);
		return maptoresponse(updatedPayment);
	}
	
	

}
