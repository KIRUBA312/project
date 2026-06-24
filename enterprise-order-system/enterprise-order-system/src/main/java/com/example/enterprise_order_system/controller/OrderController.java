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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enterprise_order_system.dto.OrderRequestDto;
import com.example.enterprise_order_system.dto.OrderResponseDto;
import com.example.enterprise_order_system.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrderResponseDto> createOrder(
			@Valid @RequestBody OrderRequestDto request,
			@RequestHeader("Idempotency-Key")
			String idempotencyKey){
		return new ResponseEntity<>(
				orderService.createOrder(request,idempotencyKey),
				HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponseDto> getOrder(
			@PathVariable Long id){
		return ResponseEntity.ok(orderService.getOrderById(id));
	}
	@GetMapping
	public ResponseEntity<List<OrderResponseDto>> getAllOrders(){
		return ResponseEntity.ok(orderService.getAllOrders());
	}
	@PutMapping("/{id}")
	public ResponseEntity<OrderResponseDto> updateOrder(
			@PathVariable Long id,@RequestBody OrderRequestDto request){
		return ResponseEntity.ok(orderService
				.updateOrder(id,request));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Long id){
		orderService.deleteOrder(id);
		return ResponseEntity.ok("Order Deleted SuccessFully");
	}
}
