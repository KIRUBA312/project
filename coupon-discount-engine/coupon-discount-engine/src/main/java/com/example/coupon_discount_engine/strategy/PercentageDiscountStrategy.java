package com.example.coupon_discount_engine.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.example.coupon_discount_engine.entity.Coupon;
import com.example.coupon_discount_engine.entity.Order;

@Component
public class PercentageDiscountStrategy implements DiscountStrategy{

	@Override
	public BigDecimal applyDiscount(Coupon coupon, BigDecimal orderAmount) {
		// TODO Auto-generated method stub
		BigDecimal discount = orderAmount.multiply(coupon.getDiscountValue())
				.divide(BigDecimal.valueOf(100),2,RoundingMode.HALF_UP);
		if (coupon.getMaximumDiscount() != null && discount.compareTo(coupon.getMaximumDiscount())>0) {
			discount = coupon.getMaximumDiscount();
			
		}
		return discount;
	}

	

}
