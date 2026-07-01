package com.example.coupon_discount_engine.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.coupon_discount_engine.enums.CouponType;

import jakarta.persistence.*;

@Entity
@Table(name = "coupons")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "code", nullable = false, unique = true, length = 100)
	private String code;
	
	@Column(name = "coupon_name",nullable = false,length = 100)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "coupon_type",nullable = false)
	private CouponType type;
	
	@Column(name = "discount_value", nullable = false, precision = 10, scale = 2)
	private BigDecimal discountValue;
	
	@Column(name = "minimum_order_amount",precision = 10, scale = 2)
	private BigDecimal minimumOrderAmount;
	
	@Column(name = "maximum_discount",precision = 10,scale = 2)
	private BigDecimal maximumDiscount;
	
	@Column(name = "product_category",length = 100)
	private String productCategory;
	
	@Column(name = "expiry_date",nullable = false)
	private LocalDate expiryDate;
	
	@Column(name = "usage_limit")
	private Integer usageLimit;
	
	@Column(name = "used_count")
	private Integer usedCount;
	
	@Column(name = "reusable")
	private Boolean reusable;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "created_at")
	private LocalDateTime created_At;
	
	@Column(name = "updated_at")
	private LocalDateTime updated_At;
	
	@OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserCoupon> userCoupons;
	@OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderDiscount> orderDiscounts;
	
	public Coupon() {
		
	}
	
	public Coupon(Long id, String code, String name, CouponType type, BigDecimal discountValue,
			BigDecimal minimumOrderAmount, BigDecimal maximumDiscount, String productCategory, LocalDate expiryDate,
			Integer usageLimit, Integer usedCount, Boolean reusable, Boolean active, LocalDateTime created_At,
			LocalDateTime updated_At, List<UserCoupon> userCoupons, List<OrderDiscount> orderDiscounts) {
		
		this.id = id;
		this.code = code;
		this.name = name;
		this.type = type;
		this.discountValue = discountValue;
		this.minimumOrderAmount = minimumOrderAmount;
		this.maximumDiscount = maximumDiscount;
		this.productCategory = productCategory;
		this.expiryDate = expiryDate;
		this.usageLimit = usageLimit;
		this.usedCount = usedCount;
		this.reusable = reusable;
		this.active = active;
		this.created_At = created_At;
		this.updated_At = updated_At;
		this.userCoupons = userCoupons;
		this.orderDiscounts = orderDiscounts;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
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

	public LocalDateTime getCreated_At() {
		return created_At;
	}

	public void setCreated_At(LocalDateTime created_At) {
		this.created_At = created_At;
	}

	public LocalDateTime getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(LocalDateTime updated_At) {
		this.updated_At = updated_At;
	}

	public List<UserCoupon> getUserCoupons() {
		return userCoupons;
	}

	public void setUserCoupons(List<UserCoupon> userCoupons) {
		this.userCoupons = userCoupons;
	}

	public List<OrderDiscount> getOrderDiscounts() {
		return orderDiscounts;
	}

	public void setOrderDiscounts(List<OrderDiscount> orderDiscounts) {
		this.orderDiscounts = orderDiscounts;
	}
	
	@Override
	public String toString() {
		return "Coupon [id=" + id +
				", code="+code+
				", type="+type +
				", discountValue="+discountValue+
				", minimumOrderAmount="+minimumOrderAmount+
				", maximumdiscount="+maximumDiscount+
				", productCategory="+productCategory+
				", expiryDate="+expiryDate+
				", usageLimit=" +usageLimit+
				", used_count="+usedCount+
				", reusable="+reusable+
				", active="+active+"]";
	}
	
	
	
	
	
}
