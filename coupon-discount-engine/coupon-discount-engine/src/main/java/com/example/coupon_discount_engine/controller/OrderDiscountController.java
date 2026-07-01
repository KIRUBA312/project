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

import com.example.coupon_discount_engine.dto.OrderDiscountRequestDto;
import com.example.coupon_discount_engine.dto.OrderDiscountResponseDto;
import com.example.coupon_discount_engine.service.OrderDiscountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/order-discounts")
public class OrderDiscountController {
	
	@Autowired
	private OrderDiscountService orderDiscountService;
	
	@PostMapping
	public ResponseEntity<OrderDiscountResponseDto> createOrderDiscount(
			@Valid @RequestBody OrderDiscountRequestDto request){
		return new ResponseEntity<>(
				orderDiscountService.createOrderDiscount(request),
				HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<OrderDiscountResponseDto> getOrderDiscountById(
			@PathVariable Long id){
		return ResponseEntity.ok(orderDiscountService
				.getOrderDiscountById(id));
	}
	@GetMapping
	public ResponseEntity<List<OrderDiscountResponseDto>> getAllOrderDiscounts(){
		return ResponseEntity.ok(orderDiscountService.getAllOrderDiscounts());
	}
	@PutMapping("/{id}")
	public ResponseEntity<OrderDiscountResponseDto> updateOrderDiscount(
			@PathVariable Long id,@Valid @RequestBody OrderDiscountRequestDto request){
		return ResponseEntity.ok(orderDiscountService.updateOrderDiscount(id,request));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrderDiscount(@PathVariable Long id){
		orderDiscountService.deleteOrderDiscount(id);
		return ResponseEntity.ok("Order discount deleted successfully");
	}
	
}
