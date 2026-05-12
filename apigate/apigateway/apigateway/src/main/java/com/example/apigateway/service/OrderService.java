package com.example.apigateway.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.apigateway.dto.OrderRequestDto;
import com.example.apigateway.dto.OrderResponseDto;

public interface OrderService {

	OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

	List<OrderResponseDto> getAllOrders();

	OrderResponseDto getOrderById(Long id);

	OrderResponseDto updateOrder(Long id, OrderRequestDto orderRequestDto);

	void deleteOrder(Long id);

}
