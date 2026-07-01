package com.example.coupon_discount_engine.service;

import java.util.List;

import com.example.coupon_discount_engine.dto.OrderRequestDto;
import com.example.coupon_discount_engine.dto.OrderResponseDto;

import jakarta.validation.Valid;

public interface OrderService {

	OrderResponseDto createOrder(@Valid OrderRequestDto request);

	OrderResponseDto getOrderById(Long id);

	List<OrderResponseDto> getAllOrders();

	OrderResponseDto updateOrder(Long id, @Valid OrderRequestDto request);

	void deleteOrder(Long id);

	OrderResponseDto applyCoupons(Long orderId, List<String> couponCodes);

}
