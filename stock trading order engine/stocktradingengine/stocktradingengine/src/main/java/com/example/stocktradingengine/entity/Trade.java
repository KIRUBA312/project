package com.example.stocktradingengine.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "trades")
public class Trade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "buy_order_id")
	private Order buyOrder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sell_order_id")
	private Order sellOrder;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "executed_at")
	private LocalDateTime executedAt;
	
	public Trade() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getBuyOrder() {
		return buyOrder;
	}

	public void setBuyOrder(Order buyOrder) {
		this.buyOrder = buyOrder;
	}

	public Order getSellOrder() {
		return sellOrder;
	}

	public void setSellOrder(Order sellOrder) {
		this.sellOrder = sellOrder;
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
