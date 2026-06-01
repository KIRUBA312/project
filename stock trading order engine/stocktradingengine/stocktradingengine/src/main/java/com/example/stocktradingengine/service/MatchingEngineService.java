package com.example.stocktradingengine.service;

import com.example.stocktradingengine.entity.Order;

public interface MatchingEngineService {

	void processOrder(Order order);
}
