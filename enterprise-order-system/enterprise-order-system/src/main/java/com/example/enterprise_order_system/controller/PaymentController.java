package com.example.enterprise_order_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enterprise_order_system.dto.PaymentRequestDto;
import com.example.enterprise_order_system.dto.PaymentResponseDto;
import com.example.enterprise_order_system.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping ResponseEntity<PaymentResponseDto> createPayment(
			@Valid @RequestBody PaymentRequestDto request){
		return new ResponseEntity<>(paymentService.processPayment(
				request),HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<PaymentResponseDto>> getAllPayments(){
		return ResponseEntity.ok(paymentService.getAllPayments());
	}
	@GetMapping("/{id}")
	public ResponseEntity<PaymentResponseDto> getPaymentsById(
			@PathVariable Long id){
		return ResponseEntity.ok(paymentService.getPaymentsById(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<PaymentResponseDto> updatePayment(
			@PathVariable Long id,@RequestBody PaymentRequestDto request){
		return ResponseEntity.ok(paymentService.updatePayment(id,request));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePayment(@PathVariable Long id){
		paymentService.deletePayment(id);
		return ResponseEntity.ok("Payment deleted Successfuly");
	}
	
}
