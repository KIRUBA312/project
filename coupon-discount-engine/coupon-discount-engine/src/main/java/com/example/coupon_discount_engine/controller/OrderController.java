package com.example.coupon_discount_engine.controller;

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

import com.example.coupon_discount_engine.dto.OrderRequestDto;
import com.example.coupon_discount_engine.dto.OrderResponseDto;
import com.example.coupon_discount_engine.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrderResponseDto> createOrder(@Valid
			@RequestBody OrderRequestDto request){
		return new ResponseEntity<>(orderService.createOrder(request),
				HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponseDto> getOrderById(
			@PathVariable Long id){
		return ResponseEntity.ok(orderService.getOrderById(id));
	}
	@GetMapping
	public ResponseEntity<List<OrderResponseDto>> getAllOrders(){
		return ResponseEntity.ok(orderService.getAllOrders());
	}
	@PutMapping("/{id}")
	public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long id,
			@Valid @RequestBody OrderRequestDto request){
		return ResponseEntity.ok(orderService.updateOrder(id, request));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Long id){
		orderService.deleteOrder(id);
		return ResponseEntity.ok("order deleted successfully");
	}
	@PostMapping("/{orderId}/apply-coupons")
	public ResponseEntity<OrderResponseDto> applyCoupons(
			@PathVariable Long orderId,@RequestBody List<String> couponCodes){
		return ResponseEntity.ok(orderService.applyCoupons(orderId, couponCodes));
	}
	
}
