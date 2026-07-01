package com.example.coupon_discount_engine.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "user_coupons")
public class UserCoupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coupon_id",nullable = false)
	private Coupon coupon;
	
	@Column(name = "used_flag", nullable = false)
	private Boolean usedFlag;
	@Column(name = "used_at")
	private LocalDateTime usedAt;
	public UserCoupon() {
		
	}

	public UserCoupon(Long id, User user, Coupon coupon, Boolean usedFlag) {
		super();
		this.id = id;
		this.user = user;
		this.coupon = coupon;
		this.usedFlag = usedFlag;
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

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Boolean getUsedFlag() {
		return usedFlag;
	}

	public void setUsedFlag(Boolean usedFlag) {
		this.usedFlag = usedFlag;
	}
	
	public LocalDateTime getUsedAt() {
		return usedAt;
	}

	public void setUsedAt(LocalDateTime usedAt) {
		this.usedAt = usedAt;
	}

	@Override
	public String toString() {
		return "UserCoupon [id="+id+
				", usedFlag="+usedFlag+"]";
	}

}
