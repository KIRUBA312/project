package com.example.stocktradingengine.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.stocktradingengine.enums.OrderStatus;
import com.example.stocktradingengine.enums.OrderType;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_id")
	private Stock stock;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "order_type")
	private OrderType orderType;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "remaining_quantity")
	private Integer remainingQuantity;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private OrderStatus status;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	public Order() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
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

	public Integer getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(Integer remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	

}
