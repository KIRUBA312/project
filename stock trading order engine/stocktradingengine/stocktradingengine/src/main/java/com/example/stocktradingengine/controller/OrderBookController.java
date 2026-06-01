package com.example.stocktradingengine.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stocktradingengine.dto.OrderBookResponseDto;
import com.example.stocktradingengine.dto.OrderResponseDto;
import com.example.stocktradingengine.entity.Order;
import com.example.stocktradingengine.orderbook.OrderBook;
import com.example.stocktradingengine.orderbook.OrderBookManager;

@RestController
@RequestMapping("/api/orderbook")
public class OrderBookController {
	
	@Autowired
	private OrderBookManager orderBookManager;

	@GetMapping("/{symbol}")
	private OrderBookResponseDto getOrder(
			@PathVariable String symbol) {
		OrderBook orderBook = orderBookManager.getOrderBook(symbol);
		OrderBookResponseDto dto = new OrderBookResponseDto();
		dto.setStockSymbol(symbol);
		dto.setBuyOrders(orderBook.getBuyOrders()
				.stream().map(this::mapOrder)
				.collect(Collectors.toList()));
		
		dto.setSellOrders(orderBook.getSellOrders()
				.stream().map(this::mapOrder)
				.collect(Collectors.toList()));
		return dto;
	}
	private OrderResponseDto mapOrder(Order order) {
		OrderResponseDto dto = new OrderResponseDto();
		dto.setId(order.getId());
		dto.setUsername(order.getUser().getName());
		dto.setStockSymbol(order.getStock().getSymbol());
		dto.setOrderType(order.getOrderType().name());
		dto.setPrice(order.getPrice());
		dto.setQuantity(order.getQuantity());
		dto.setRemainingQuantity(order.getRemainingQuantity());
		dto.setCreatedAt(order.getCreatedAt());
		
		return dto;
		
	}
	
}
