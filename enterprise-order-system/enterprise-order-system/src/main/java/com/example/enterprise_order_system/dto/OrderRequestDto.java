package com.example.enterprise_order_system.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderRequestDto {

	@NotNull(message = "User Id is required")
	private Long userId;
	@NotBlank
	private String productName;
	@Positive(message = "Total must be greater than zero")
	private Integer quantity;
	@NotNull
	@Positive(message = "Total must be greater than zero")
	private BigDecimal total;
	@NotBlank
	private String status;
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public BigDecimal getTotal() {
		return total;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}
