package com.example.enterprise_order_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprise_order_system.common.constants.OrderStatus;
import com.example.enterprise_order_system.entity.Order;
import com.example.enterprise_order_system.exception.ResourceNotFoundException;
import com.example.enterprise_order_system.repository.OrderRepository;

@Service
public class SagaService {

	@Autowired
	private OrderRepository orderRepository;
	
	public void handlePaymentSuccess(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Order not found"));
		order.setStatus(OrderStatus.PAID.name());
		
		orderRepository.save(order);
	}
	public void handlePaymentFailure(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() ->new ResourceNotFoundException(
						"Order not found"));
		order.setStatus(OrderStatus.FAILED.name());
		orderRepository.save(order);
	}
	
	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Order not found"));
		order.setStatus(OrderStatus.CANCELLED.name());
		orderRepository.save(order);
	}
	
}
