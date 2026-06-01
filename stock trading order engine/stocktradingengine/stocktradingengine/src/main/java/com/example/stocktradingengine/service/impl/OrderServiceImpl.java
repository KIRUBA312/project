package com.example.stocktradingengine.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stocktradingengine.dto.BuyOrderRequestDto;
import com.example.stocktradingengine.dto.OrderResponseDto;
import com.example.stocktradingengine.dto.SellOrderRequestDto;
import com.example.stocktradingengine.entity.Order;
import com.example.stocktradingengine.entity.Stock;
import com.example.stocktradingengine.entity.User;
import com.example.stocktradingengine.enums.OrderStatus;
import com.example.stocktradingengine.enums.OrderType;
import com.example.stocktradingengine.exception.InvalidOrderException;
import com.example.stocktradingengine.exception.ResourceNotFoundException;
import com.example.stocktradingengine.repository.OrderRepository;
import com.example.stocktradingengine.repository.StockRepository;
import com.example.stocktradingengine.repository.UserRepository;
import com.example.stocktradingengine.service.MatchingEngineService;
import com.example.stocktradingengine.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private MatchingEngineService matchingEngineService;
	
	
	@Override
	public OrderResponseDto placeBuyOrder(BuyOrderRequestDto dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() ->new ResourceNotFoundException("User not found"));
		Stock stock = stockRepository.findById(dto.getStockId())
				.orElseThrow(() ->new ResourceNotFoundException("Stock not found"));
		Order order = new Order();
		order.setUser(user);
		order.setStock(stock);
		order.setOrderType(OrderType.BUY);
		order.setPrice(dto.getPrice());
		order.setQuantity(dto.getQuantity());
		order.setRemainingQuantity(dto.getQuantity());
		order.setStatus(OrderStatus.OPEN);
		order.setCreatedAt(LocalDateTime.now());
		order = orderRepository.save(order);
		matchingEngineService.processOrder(order);
		return maptoResponse(order);
	}
	@Override
	public OrderResponseDto placeSellOrder(SellOrderRequestDto dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() ->
				new ResourceNotFoundException("User not found"));
		Stock stock = stockRepository.findById(dto.getStockId())
				.orElseThrow(() ->
				new ResourceNotFoundException("Stock not found"));
		Order order = new Order();
		order.setUser(user);
		order.setStock(stock);
		order.setOrderType(OrderType.SELL);
		order.setPrice(dto.getPrice());
		order.setQuantity(dto.getQuantity());
		order.setRemainingQuantity(dto.getQuantity());
		order.setStatus(OrderStatus.OPEN);
		order.setCreatedAt(LocalDateTime.now());
		order = orderRepository.save(order);
		matchingEngineService.processOrder(order);
		return maptoResponse(order);
	}
	@Override
	public OrderResponseDto getOrderById(Long id) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("order not found"));
		return maptoResponse(order);
	}
	
	@Override
	public List<OrderResponseDto> getOrderByUser(Long userid) {
		// TODO Auto-generated method stub
		return orderRepository.findByUserId(userid).stream()
				.map(this::maptoResponse)
				.collect(Collectors.toList());
	}
	@Override
	public String cancelOrder(Long id) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException("order not found"));
		if (order.getStatus() == OrderStatus.FILLED) {
			throw new InvalidOrderException("Filled order cannot be cancelled");
			
		}
		
		order.setStatus(OrderStatus.CANCELLED);
		orderRepository.save(order);
		
		return "Order cancelled successfully";
	}
	private OrderResponseDto maptoResponse(Order order) {
		// TODO Auto-generated method stub
		OrderResponseDto dto = new OrderResponseDto();
		dto.setId(order.getId());
		dto.setUsername(order.getUser().getName());
		dto.setStockSymbol(order.getStock().getSymbol());
		dto.setOrderType(order.getOrderType().name());
		dto.setPrice(order.getPrice());
		dto.setQuantity(order.getQuantity());
		dto.setRemainingQuantity(order.getRemainingQuantity());
		dto.setStatus(order.getStatus().name());
		dto.setCreatedAt(order.getCreatedAt());
		
		return dto;
	}
	
	
}
