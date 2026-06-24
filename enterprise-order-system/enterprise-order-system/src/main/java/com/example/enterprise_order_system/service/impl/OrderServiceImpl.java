package com.example.enterprise_order_system.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprise_order_system.common.constants.KafkaTopics;
import com.example.enterprise_order_system.common.constants.OrderStatus;
import com.example.enterprise_order_system.dto.OrderRequestDto;
import com.example.enterprise_order_system.dto.OrderResponseDto;
import com.example.enterprise_order_system.entity.Inventory;
import com.example.enterprise_order_system.entity.Order;
import com.example.enterprise_order_system.exception.DuplicateOrderException;
import com.example.enterprise_order_system.exception.InventoryException;
import com.example.enterprise_order_system.exception.ResourceNotFoundException;
import com.example.enterprise_order_system.repository.InventoryRepository;
import com.example.enterprise_order_system.repository.OrderRepository;
import com.example.enterprise_order_system.service.KafkaProducerService;
import com.example.enterprise_order_system.service.OrderService;
import com.example.enterprise_order_system.service.OutboxService;

import jakarta.validation.Valid;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private KafkaProducerService kafkaProducerService;
	@Autowired
	private OutboxService outboxService;
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Override
	public OrderResponseDto createOrder(@Valid OrderRequestDto request, 
			String idempotencyKey) {
		// TODO Auto-generated method stub
		if(orderRepository.existsByIdempotencyKey(idempotencyKey)) {
			throw new DuplicateOrderException(
					"Duplicate Order Request");
		}
		Inventory inventory = inventoryRepository.findByProductName(
				request.getProductName()).orElseThrow(() ->
				new InventoryException("product not found"));
		if(inventory.getAvailableQuantity()<request.getQuantity()) {
			throw new InventoryException("Insufficient Stock Available");
		}
		inventory.setAvailableQuantity(inventory.getAvailableQuantity()
				-request.getQuantity());
		inventoryRepository.save(inventory);
		Order order = new Order();
		order.setUserId(request.getUserId());
		order.setProductName(request.getProductName());
		order.setQuantity(request.getQuantity());
		order.setTotal(request.getTotal());
		order.setStatus(OrderStatus.CREATED.name());
		order.setIdempotencyKey(idempotencyKey);
		order.setCreatedAt(LocalDateTime.now());
		Order savedOrder = orderRepository.save(order);
		outboxService.saveEvent("ORDER",
				savedOrder.getId(),
				KafkaTopics.ORDER_CREATED,
				"Order Created");
		kafkaProducerService.publishMessage(KafkaTopics.ORDER_CREATED,
				"Order Id : "+savedOrder.getId());
		return maptoresponse(savedOrder);
	}
	@Override
	public OrderResponseDto getOrderById(Long id) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Order not found"));
		return maptoresponse(order);
	}
	@Override
	public List<OrderResponseDto> getAllOrders() {
		// TODO Auto-generated method stub
		return orderRepository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	@Override
	public OrderResponseDto updateOrder(Long id, OrderRequestDto request) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Order not found"));
		order.setTotal(request.getTotal());
		return maptoresponse(orderRepository.save(order));
	}
	@Override
	public void deleteOrder(Long id) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Order not found"));
		orderRepository.delete(order);
		
	}
	
	private OrderResponseDto maptoresponse(Order order) {
		OrderResponseDto response = new OrderResponseDto();
		response.setOrderId(order.getId());
		response.setProductName(order.getProductName());
		response.setStatus(order.getStatus());
		response.setTotal(order.getTotal());
		
		return response;
	}
	
	
}
