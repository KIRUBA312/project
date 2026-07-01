package com.example.coupon_discount_engine.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ApplyCouponRequestDto {

	@NotNull
	private Long orderId;
	@NotNull
	private Long userId;
	private List<String> couponCodes;
	@NotBlank
	private String idempotencyKey;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<String> getCouponCodes() {
		return couponCodes;
	}
	public void setCouponCodes(List<String> couponCodes) {
		this.couponCodes = couponCodes;
	}
	public String getIdempotencyKey() {
		return idempotencyKey;
	}
	public void setIdempotencyKey(String idempotencyKey) {
		this.idempotencyKey = idempotencyKey;
	}
	
	
}
