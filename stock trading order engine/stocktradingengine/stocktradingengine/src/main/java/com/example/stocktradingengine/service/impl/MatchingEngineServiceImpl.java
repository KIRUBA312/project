package com.example.stocktradingengine.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stocktradingengine.entity.Order;
import com.example.stocktradingengine.entity.Trade;
import com.example.stocktradingengine.enums.OrderStatus;
import com.example.stocktradingengine.enums.OrderType;
import com.example.stocktradingengine.orderbook.OrderBook;
import com.example.stocktradingengine.orderbook.OrderBookManager;
import com.example.stocktradingengine.repository.OrderRepository;
import com.example.stocktradingengine.repository.TradeRepository;
import com.example.stocktradingengine.service.MatchingEngineService;

import jakarta.transaction.Transactional;
@Service
public class MatchingEngineServiceImpl implements MatchingEngineService{

	@Autowired
	private OrderBookManager orderBookManager;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private TradeRepository tradeRepository;
	
	@Override
	@Transactional
	public void processOrder(Order order) {
		// TODO Auto-generated method stub
		String stockSymbol = order.getStock().getSymbol();
		OrderBook orderBook = orderBookManager.getOrderBook(stockSymbol);
		
		if (order.getOrderType() == OrderType.BUY) {
			processBuyOrder(order, orderBook);
		}else {
			processSellOrder(order, orderBook);
		}
		
	}
	private void processBuyOrder(Order buyOrder, OrderBook orderBook) {
		// TODO Auto-generated method stub
		while(!orderBook.getBuyOrders().isEmpty() &&
				buyOrder.getRemainingQuantity()>0) {
			Order sellOrder = orderBook.getSellOrders().peek();
			if(buyOrder.getPrice()
					.compareTo(sellOrder.getPrice())<0) {
				break;
			}
			executeTrade(buyOrder,sellOrder);
			if (sellOrder.getRemainingQuantity()==0) {
				orderBook.getSellOrders().poll();
			}
		}
		if (buyOrder.getRemainingQuantity()>0) {
			orderBook.getBuyOrders().offer(buyOrder);
		}
		
	}
	

	private void processSellOrder(Order sellOrder, OrderBook orderBook) {
		// TODO Auto-generated method stub
		while(!orderBook.getBuyOrders().isEmpty() && sellOrder.getRemainingQuantity()>0) {
			Order buyOrder = orderBook.getBuyOrders().peek();
			if (buyOrder.getPrice().compareTo(sellOrder.getPrice())<0) {
				break;
			}
			executeTrade(buyOrder,sellOrder);
			if (sellOrder.getRemainingQuantity()>0) {
				orderBook.getSellOrders().offer(sellOrder);
			}
		}
		
	}
	private void executeTrade(Order buyOrder, Order sellOrder) {
		// TODO Auto-generated method stub
		Integer tradeQuantity = Math.min(buyOrder.getRemainingQuantity(), 
				sellOrder.getRemainingQuantity());
		buyOrder.setRemainingQuantity(buyOrder.getRemainingQuantity()-tradeQuantity);
		sellOrder.setRemainingQuantity(sellOrder.getRemainingQuantity()-tradeQuantity);
		updateOrderStatus(buyOrder);
		updateOrderStatus(sellOrder);
		
		Trade trade = new Trade();
		trade.setBuyOrder(buyOrder);
		trade.setSellOrder(sellOrder);
		
		trade.setPrice(sellOrder.getPrice());
		trade.setQuantity(tradeQuantity);
		trade.setExecutedAt(LocalDateTime.now());
		
		tradeRepository.save(trade);
		
		orderRepository.save(buyOrder);
		orderRepository.save(sellOrder);
		
	}
	private void updateOrderStatus(Order order) {
		// TODO Auto-generated method stub
		if(order.getRemainingQuantity()==0) {
			order.setStatus(OrderStatus.FILLED);
		}
		else if(order.getRemainingQuantity()<order.getQuantity()) {
			order.setStatus(OrderStatus.PARTIAL);
		}else {
			order.setStatus(OrderStatus.OPEN);
		}
		
	}

	
	
}
