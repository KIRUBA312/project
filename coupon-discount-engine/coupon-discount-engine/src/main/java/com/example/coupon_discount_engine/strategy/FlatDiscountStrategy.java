package com.example.coupon_discount_engine.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.example.coupon_discount_engine.entity.Coupon;
import com.example.coupon_discount_engine.entity.Order;

@Component
public class FlatDiscountStrategy implements DiscountStrategy{

	@Override
	public BigDecimal applyDiscount( Coupon coupon, BigDecimal Order) {
		// TODO Auto-generated method stub
//		BigDecimal total = coupon.getDiscountValue();
		BigDecimal discount = coupon.getDiscountValue();
		if (coupon.getMaximumDiscount() != null 
				&& discount.compareTo(coupon.getMaximumDiscount())>0) {
			discount = coupon.getMaximumDiscount();
			
		}
		if(discount.compareTo(Order)>0) {
			discount = Order;
		}
		return discount;
	}

}
