package com.example.stocktradingengine.orderbook;

import java.util.PriorityQueue;

import com.example.stocktradingengine.entity.Order;

public class OrderBook {

	private PriorityQueue<Order> buyOrders;
	private PriorityQueue<Order> sellOrders;
	
	public OrderBook() {
		buyOrders = new PriorityQueue<>(new BuyOrderComparator());
		sellOrders = new PriorityQueue<>(new SellOrderComparator());
	}
	public PriorityQueue<Order> getBuyOrders(){
		return buyOrders;
	}
	public PriorityQueue<Order> getSellOrders() {
		return sellOrders;
	}
	public void setSellOrders(PriorityQueue<Order> sellOrders) {
		this.sellOrders = sellOrders;
	}
	public void setBuyOrders(PriorityQueue<Order> buyOrders) {
		this.buyOrders = buyOrders;
	}
	
}
