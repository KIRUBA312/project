package com.example.coupon_discount_engine.exception;

public class CouponAlreadyUsedException extends RuntimeException{

	public CouponAlreadyUsedException(String message) {
		super(message);
	}
}
