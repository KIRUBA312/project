package com.example.coupon_discount_engine.exception;

public class CouponExpiredException extends RuntimeException{

	public CouponExpiredException(String message) {
		super(message);
	}
}
