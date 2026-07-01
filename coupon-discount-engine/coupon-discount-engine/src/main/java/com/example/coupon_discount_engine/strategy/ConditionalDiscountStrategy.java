package com.example.coupon_discount_engine.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.example.coupon_discount_engine.entity.Coupon;
import com.example.coupon_discount_engine.entity.Order;

@Component
public class ConditionalDiscountStrategy implements DiscountStrategy{

	@Override
	public BigDecimal applyDiscount(Coupon coupon, BigDecimal orderAmount) {
		// TODO Auto-generated method stub
		if (coupon.getMinimumOrderAmount()==null) {
			return BigDecimal.ZERO;
		}
		if (orderAmount.compareTo(coupon.getMinimumOrderAmount())<0) {
			return BigDecimal.ZERO;
		}
		BigDecimal discount = coupon.getDiscountValue();
		if (coupon.getMaximumDiscount()!=null && discount.compareTo(coupon.getMaximumDiscount())>0) {
			discount = coupon.getMaximumDiscount();
		}
		return discount;
	}

	
	

}
