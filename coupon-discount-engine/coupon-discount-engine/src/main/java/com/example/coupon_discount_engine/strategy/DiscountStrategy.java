package com.example.coupon_discount_engine.strategy;

import java.math.BigDecimal;

import com.example.coupon_discount_engine.entity.Coupon;
import com.example.coupon_discount_engine.entity.Order;

public interface DiscountStrategy {

	BigDecimal applyDiscount(Coupon coupon, BigDecimal orderAmount);
	
}
