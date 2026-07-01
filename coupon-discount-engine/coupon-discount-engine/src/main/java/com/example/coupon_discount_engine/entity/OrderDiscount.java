package com.example.coupon_discount_engine.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "order_discount")
public class OrderDiscount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id",nullable = false)
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coupon_id",nullable = false)
	private Coupon coupon;
	
	@Column(name = "discount_applied",nullable = false,precision = 10, scale = 2)
	private BigDecimal discountApplied;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	public OrderDiscount() {
		
	}

	public OrderDiscount(Long id, Order order, Coupon coupon, BigDecimal discountApplied, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.order = order;
		this.coupon = coupon;
		this.discountApplied = discountApplied;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public BigDecimal getDiscountApplied() {
		return discountApplied;
	}

	public void setDiscountApplied(BigDecimal discountApplied) {
		this.discountApplied = discountApplied;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "OrderDiscount [id="+id+", discountApplied="+"]";
	}
	

}
