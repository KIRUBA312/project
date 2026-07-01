package com.example.coupon_discount_engine.dto;

public class UserCouponResponseDto {

	private Long id;
	private Long userId;
	private Long couponId;
	private Boolean usedFlag;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Boolean getUsedFlag() {
		return usedFlag;
	}
	public void setUsedFlag(Boolean usedFlag) {
		this.usedFlag = usedFlag;
	}
	
	
	
}
