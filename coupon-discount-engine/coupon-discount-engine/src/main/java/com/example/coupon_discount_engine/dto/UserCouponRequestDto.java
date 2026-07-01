package com.example.coupon_discount_engine.dto;

import jakarta.validation.constraints.NotNull;

public class UserCouponRequestDto {

	@NotNull
	private Long userId;
	@NotNull
	private Long couponId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCouponId() {
		return couponId;
	}
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	
	
	
}
