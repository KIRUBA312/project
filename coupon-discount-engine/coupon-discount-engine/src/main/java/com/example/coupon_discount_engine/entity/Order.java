package com.example.coupon_discount_engine.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.coupon_discount_engine.enums.OrderStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name = "order_number",nullable = false, unique = true,length = 50)
	private String orderNumber;
	
	@Column(name = "total_amount",nullable = false, precision = 12,scale = 2)
	private BigDecimal totalAmount;
	
	@Column(name = "final_amount",nullable = false, precision = 12, scale = 2)
	private BigDecimal finalAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "order_status")
	private OrderStatus orderStatus;
	
	@Column(name = "product_category", nullable = false, length = 100)
	private String productCategory;
	
	@Column(name = "idempotency_key", nullable = false, unique = true, length = 100)
	private String idempotencyKey;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderDiscount> orderDiscounts;
	
	public Order() {
		
	}

	public Order(Long id, User user, String orderNumber, BigDecimal totalAmount, BigDecimal finalAmount,
			OrderStatus orderStatus, String productCategory, String idempotencyKey, List<OrderDiscount> orderDiscounts) {
		super();
		this.id = id;
		this.user = user;
		this.orderNumber = orderNumber;
		this.totalAmount = totalAmount;
		this.finalAmount = finalAmount;
		this.orderStatus = orderStatus;
		this.productCategory = productCategory;
		this.idempotencyKey = idempotencyKey;
		this.orderDiscounts = orderDiscounts;
	}

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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getIdempotencyKey() {
		return idempotencyKey;
	}

	public void setIdempotencyKey(String idempotencyKey) {
		this.idempotencyKey = idempotencyKey;
	}
	

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<OrderDiscount> getOrderDiscounts() {
		return orderDiscounts;
	}

	public void setOrderDiscounts(List<OrderDiscount> orderDiscounts) {
		this.orderDiscounts = orderDiscounts;
	}
	
	@Override
	public String toString() {
		return "Order [id="+id+", productCategory="+productCategory+
				",totalAmount="+totalAmount+"finalAmount="+finalAmount
				+",idempotencyKey="+idempotencyKey+"]";
	}

}
