package com.example.stocktradingengine.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TradeResponseDto {

	private Long id;
	private Long buyOrderId;
	private Long sellOrderId;
	private BigDecimal price;
	private Integer quantity;
	private LocalDateTime executedAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBuyOrderId() {
		return buyOrderId;
	}
	public void setBuyOrderId(Long buyOrderId) {
		this.buyOrderId = buyOrderId;
	}
	public Long getSellOrderId() {
		return sellOrderId;
	}
	public void setSellOrderId(Long sellOrderId) {
		this.sellOrderId = sellOrderId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public LocalDateTime getExecutedAt() {
		return executedAt;
	}
	public void setExecutedAt(LocalDateTime executedAt) {
		this.executedAt = executedAt;
	}
	
	
	
}
