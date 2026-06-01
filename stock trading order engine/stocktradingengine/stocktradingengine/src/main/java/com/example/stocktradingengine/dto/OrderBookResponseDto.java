package com.example.stocktradingengine.dto;

import java.util.List;

public class OrderBookResponseDto {

	private String stockSymbol;
	private List<OrderResponseDto> buyOrders;
	private List<OrderResponseDto> sellOrders;
	
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public List<OrderResponseDto> getBuyOrders() {
		return buyOrders;
	}
	public void setBuyOrders(List<OrderResponseDto> buyOrders) {
		this.buyOrders = buyOrders;
	}
	public List<OrderResponseDto> getSellOrders() {
		return sellOrders;
	}
	public void setSellOrders(List<OrderResponseDto> sellOrders) {
		this.sellOrders = sellOrders;
	}
	
	
	
}
