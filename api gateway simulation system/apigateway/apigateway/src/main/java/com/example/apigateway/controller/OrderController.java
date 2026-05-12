package com.example.apigateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.apigateway.dto.OrderRequestDto;
import com.example.apigateway.dto.OrderResponseDto;
import com.example.apigateway.service.OrderService;
import com.example.apigateway.util.ApiKeyValidator;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrderResponseDto> createOrder(
			@RequestBody OrderRequestDto orderRequestDto,
			@RequestHeader(value = "X-API-KEY",required = false)
			String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		
		return ResponseEntity.ok(orderService.createOrder(orderRequestDto));
		
	}
	
	@GetMapping
	public ResponseEntity<List<OrderResponseDto>> getAllOrders(
			@RequestHeader(value = "X-API-KEY",required = false)String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		return ResponseEntity.ok(orderService.getAllOrders());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponseDto> getOrderById(
			@PathVariable Long id,@RequestHeader(value = "X-API-KEY",required = false)
			String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		
		return ResponseEntity.ok(orderService.getOrderById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderResponseDto> updateOrder(
			@PathVariable Long id,
			@RequestBody OrderRequestDto orderRequestDto,
			@RequestHeader(value = "X-API-KEY",required = false)
			String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		
		return ResponseEntity.ok(orderService.updateOrder(id, orderRequestDto));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(
			@PathVariable Long id,
			@RequestHeader(value = "X-API-KEY",required = false)
			String apiKey){
		ApiKeyValidator.validate(apiKey);
		orderService.deleteOrder(id);
		return ResponseEntity.ok("Order Deleted Successfully");
	}
	
	
}
