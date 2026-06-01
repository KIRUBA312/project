package com.example.stocktradingengine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.stocktradingengine.orderbook.OrderBookManager;

@Configuration
public class OrderBookConfig {

	@Bean
	public OrderBookManager orderBookManager() {
		
		return new OrderBookManager();
	}
	
}
