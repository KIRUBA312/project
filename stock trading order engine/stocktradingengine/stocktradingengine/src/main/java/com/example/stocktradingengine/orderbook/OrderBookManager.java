package com.example.stocktradingengine.orderbook;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class OrderBookManager {

	private final Map<String, OrderBook> orderBooks = 
			new ConcurrentHashMap<>();

	public OrderBook getOrderBook(String stockSymbol) {
		return orderBooks.computeIfAbsent(stockSymbol,
				symbol -> new OrderBook());
	}
	
	public Map<String, OrderBook> getOrderBooks() {
		return orderBooks; 
	}
	
		
}
