package com.example.apigateway.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apigateway.dto.OrderRequestDto;
import com.example.apigateway.dto.OrderResponseDto;
import com.example.apigateway.entity.Orders;
import com.example.apigateway.entity.RequestLog;
import com.example.apigateway.repository.OrderRepository;
import com.example.apigateway.service.LogService;
import com.example.apigateway.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private LogService logService;

	@Override
	public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
		// TODO Auto-generated method stub
		Orders order = new Orders();
		
		order.setOrderNumberString(orderRequestDto.getOrderNumber());
		order.setAmount(orderRequestDto.getAmount());
		order.setOrderStatus(orderRequestDto.getOrderStatus());
		
		 Orders saveOrder = orderRepository.save(order);
		 saveLog("/api/orders","POST");
		 
		 return maptoDto(saveOrder);
		
	}

	@Override
	public List<OrderResponseDto> getAllOrders() {
		// TODO Auto-generated method stub
		List<Orders> orderList = orderRepository.findAll();
		List<OrderResponseDto> responseList = new ArrayList<>();
		
		for(Orders order: orderList) {
			responseList.add(maptoDto(order));
		}
		
		saveLog("/api/orders","GET");
		
		return responseList;
	}

	@Override
	public OrderResponseDto getOrderById(Long id) {
		// TODO Auto-generated method stub
		Orders orders = orderRepository.findById(id).orElseThrow();
		saveLog("/api/orders/"+id,"GET");
		return maptoDto(orders);
	}

	@Override
	public OrderResponseDto updateOrder(Long id, OrderRequestDto orderRequestDto) {
		// TODO Auto-generated method stub
		Orders orders = orderRepository.findById(id).orElseThrow();
		
		orders.setOrderNumberString(orderRequestDto.getOrderNumber());
		orders.setAmount(orderRequestDto.getAmount());
		orders.setOrderStatus(orderRequestDto.getOrderStatus());
		
		Orders updateOrders = orderRepository.save(orders);
		saveLog("/api/orders/"+id,"PUT");
		return maptoDto(updateOrders);
	}

	@Override
	public void deleteOrder(Long id) {
		// TODO Auto-generated method stub
		orderRepository.deleteById(id);
		saveLog("/api/orders/"+id,"DELETE");
		
	}
	
	private OrderResponseDto maptoDto(Orders orders) {
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		
		orderResponseDto.setId(orders.getId());
		orderResponseDto.setOrderNumber(orders.getOrderNumberString());
		orderResponseDto.setAmount(orders.getAmount());
		orderResponseDto.setOrderStatus(orders.getOrderStatus());
		
		return orderResponseDto;
	}
	
	private void saveLog(String path, String method) {
		
		RequestLog log = new RequestLog();
		
		log.setRequestPath(path);
		log.setMethod(method);
		log.setTimestamp(LocalDateTime.now());
		log.setStatus("SUCCESS");
		
		logService.saveLog(log);
	}
	
}
