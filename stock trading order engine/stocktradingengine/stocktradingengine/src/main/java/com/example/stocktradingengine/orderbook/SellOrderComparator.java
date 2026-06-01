package com.example.stocktradingengine.orderbook;

import java.util.Comparator;

import com.example.stocktradingengine.entity.Order;

public class SellOrderComparator implements Comparator<Order>{

	@Override
	public int compare(Order o1, Order o2) {
		// TODO Auto-generated method stub
		int priceComparison = o1.getPrice().compareTo(o2.getPrice());
		if (priceComparison !=0) {
			
			return priceComparison;
		}
		
		return o1.getCreatedAt().compareTo(o2.getCreatedAt());
	}
	
	

}
