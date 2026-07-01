package com.example.coupon_discount_engine.dto;

import java.math.BigDecimal;
import java.util.List;

public class ApplyCouponResponseDto {

	private Long orderId;
	private BigDecimal originalAmount;
	private BigDecimal totalDiscount;
	private BigDecimal finalAmount;
	private List<String> appliedCoupons;
	private String message;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getOriginalAmount() {
		return originalAmount;
	}
	public void setOriginalAmount(BigDecimal originalAmount) {
		this.originalAmount = originalAmount;
	}
	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public BigDecimal getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}
	public List<String> getAppliedCoupons() {
		return appliedCoupons;
	}
	public void setAppliedCoupons(List<String> appliedCoupons) {
		this.appliedCoupons = appliedCoupons;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
