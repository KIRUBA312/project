package com.example.coupon_discount_engine.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.coupon_discount_engine.enums.CouponType;

public class CouponResponseDto {

	private Long id;
	private String code;
	private String couponName;
	private CouponType couponType;
	private BigDecimal discountValue;
	private BigDecimal minimumOrderAmount;
	private BigDecimal maximumDiscount;
	private String productCategory;
	private LocalDate expiryDate;
	private Integer usageLimit;
	private Integer usedCount;
	private Boolean reusable;
	private Boolean active;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	
	public CouponType getCouponType() {
		return couponType;
	}
	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}
	public BigDecimal getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(BigDecimal bigDecimal) {
		this.discountValue = bigDecimal;
	}
	public BigDecimal getMinimumOrderAmount() {
		return minimumOrderAmount;
	}
	public void setMinimumOrderAmount(BigDecimal minimumOrderAmount) {
		this.minimumOrderAmount = minimumOrderAmount;
	}
	public BigDecimal getMaximumDiscount() {
		return maximumDiscount;
	}
	public void setMaximumDiscount(BigDecimal maximumDiscount) {
		this.maximumDiscount = maximumDiscount;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Integer getUsageLimit() {
		return usageLimit;
	}
	public void setUsageLimit(Integer usageLimit) {
		this.usageLimit = usageLimit;
	}
	public Integer getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}
	public Boolean getReusable() {
		return reusable;
	}
	public void setReusable(Boolean reusable) {
		this.reusable = reusable;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
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
	
	
	
}
