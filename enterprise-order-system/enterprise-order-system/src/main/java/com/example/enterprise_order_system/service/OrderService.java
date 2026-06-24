package com.example.enterprise_order_system.service;

import java.util.List;

import com.example.enterprise_order_system.dto.OrderRequestDto;
import com.example.enterprise_order_system.dto.OrderResponseDto;

import jakarta.validation.Valid;

public interface OrderService {

	OrderResponseDto createOrder(@Valid OrderRequestDto request, 
			String idempotencyKey);

	OrderResponseDto getOrderById(Long id);

	List<OrderResponseDto> getAllOrders();

	OrderResponseDto updateOrder(Long id, OrderRequestDto request);

	void deleteOrder(Long id);

}
