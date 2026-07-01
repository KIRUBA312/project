package com.example.coupon_discount_engine.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.coupon_discount_engine.enums.CouponType;

@Component
public class DiscountStrategyFactory {

	@Autowired
	private FlatDiscountStrategy flatDiscountStrategy;
	
	@Autowired
	private PercentageDiscountStrategy percentageDiscountStrategy;
	
	@Autowired
	private ConditionalDiscountStrategy conditionalDiscountStrategy;
	
	public DiscountStrategy getStrategy(CouponType couponType) {
		switch(couponType) {
		case FLAT:
			return flatDiscountStrategy;
		case PERCENTAGE:
			return percentageDiscountStrategy;
		case CONDITIONAL:
			return conditionalDiscountStrategy;
			
		default:
			throw new IllegalArgumentException("Invalid Coupon Type");
		}
	}
	
}
