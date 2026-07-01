package com.example.coupon_discount_engine.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import com.example.coupon_discount_engine.enums.*;

public class CouponRequestDto {

	@NotBlank
	private String code;
	
	@NotBlank
	private String couponName;
	
	@NotNull(message="Coupon Type is required")
	private CouponType couponType;
	
	@NotNull
	@DecimalMin("1.0")
	private BigDecimal discountValue;
	
	private BigDecimal minimumOrderAmount;
	private BigDecimal maximumDiscount;
	private String productCategory;
	
	@Future(message = "Expiry Date must be future")
	private LocalDate expiryDate;
	
	@Positive
	private Integer usageLimit;
	private Boolean reusable;
	private Boolean active;
	
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
	public com.example.coupon_discount_engine.enums.CouponType getCouponType() {
		return couponType;
	}
	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}
	public BigDecimal getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
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
	
	
	
	
}
