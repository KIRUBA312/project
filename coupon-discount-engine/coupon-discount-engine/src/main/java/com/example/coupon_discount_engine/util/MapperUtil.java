package com.example.coupon_discount_engine.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.coupon_discount_engine.dto.CouponResponseDto;
import com.example.coupon_discount_engine.dto.IdempotencyResponseDto;
import com.example.coupon_discount_engine.dto.OrderDiscountResponseDto;
import com.example.coupon_discount_engine.dto.OrderResponseDto;
import com.example.coupon_discount_engine.dto.UserCouponResponseDto;
import com.example.coupon_discount_engine.dto.UserResponseDto;
import com.example.coupon_discount_engine.entity.Coupon;
import com.example.coupon_discount_engine.entity.IdempotencyRequest;
import com.example.coupon_discount_engine.entity.Order;
import com.example.coupon_discount_engine.entity.OrderDiscount;
import com.example.coupon_discount_engine.entity.User;
import com.example.coupon_discount_engine.entity.UserCoupon;


@Component
public class MapperUtil {

	public <S,T> T map(S source, Class<T> targetClass) {
		try {
			T target = targetClass.getDeclaredConstructor().newInstance();
			
			BeanUtils.copyProperties(source, target);
			
			return target;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Error while mapping objects");
		}
	}

	public static UserResponseDto toUserResponse(User user) {
		// TODO Auto-generated method stub
		if (user == null) {
			return null;
		}
		UserResponseDto response = new UserResponseDto();
		response.setId(user.getId());
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setMobile(user.getMobile());
		response.setStatus(user.getStatus());
		response.setCreatedAt(user.getCreatedAt());
		response.setUpdatedAt(user.getUpdatedAt());
		
		return response;
	}
	
	public static CouponResponseDto toCouponResponse(Coupon coupon) {
		if (coupon == null) {
			return null;
		}
		
		CouponResponseDto dto = new CouponResponseDto();
		
		dto.setId(coupon.getId());
		dto.setCode(coupon.getCode());
		dto.setCouponName(coupon.getName());
		dto.setCouponType(coupon.getType());
		dto.setDiscountValue(coupon.getDiscountValue());
		dto.setMinimumOrderAmount(coupon.getMinimumOrderAmount());
		dto.setMaximumDiscount(coupon.getMaximumDiscount());
		dto.setProductCategory(coupon.getProductCategory());
		dto.setExpiryDate(coupon.getExpiryDate());
		dto.setUsageLimit(coupon.getUsageLimit());
		dto.setUsedCount(coupon.getUsedCount());
		dto.setReusable(coupon.getReusable());
		dto.setActive(coupon.getActive());
		dto.setCreatedAt(coupon.getCreated_At());
		dto.setUpdatedAt(coupon.getUpdated_At());
		
		return dto;
	}
	
	public static OrderResponseDto toOrderResponse(Order order) {
		
		if (order == null) {
			return null;
		}
		
		OrderResponseDto dto = new OrderResponseDto();
		
		dto.setId(order.getId());
		dto.setUserId(order.getUser().getId());
		dto.setOrderNumber(order.getOrderNumber());
		dto.setTotalAmount(order.getTotalAmount());
		dto.setFinalAmount(order.getFinalAmount());
		dto.setOrderStatus(order.getOrderStatus());
		dto.setProductCategory(order.getProductCategory());
		dto.setIdempotencyKey(order.getIdempotencyKey());
		
		return dto;
		
	}
	
	public static OrderDiscountResponseDto tOrderDiscountResponse(OrderDiscount orderDiscount) {
		
		if (orderDiscount == null) {
			return null;
		}
		OrderDiscountResponseDto dto = new OrderDiscountResponseDto();
		
		dto.setId(orderDiscount.getId());
		dto.setOrderId(orderDiscount.getOrder().getId());
		dto.setCouponId(orderDiscount.getCoupon().getId());
		dto.getDiscountApplied(orderDiscount.getDiscountApplied());
		dto.setCreatedAt(orderDiscount.getCreatedAt());
		
		return dto;
	}
	
	public static UserCouponResponseDto toUserCouponResponse(UserCoupon userCoupon) {
		
		if (userCoupon == null) {
			return null;
		}
		UserCouponResponseDto dto = new UserCouponResponseDto();
		
		dto.setId(userCoupon.getId());
		dto.setUserId(userCoupon.getUser().getId());
		dto.setCouponId(userCoupon.getCoupon().getId());
		dto.setUsedFlag(userCoupon.getUsedFlag());
		
		return dto;
		
	}
	
	public static IdempotencyResponseDto toIdempotencyResponse(IdempotencyRequest request) {
		
		if (request == null) {
			return null;
		}
		
		IdempotencyResponseDto dto = new IdempotencyResponseDto();
		
		dto.setId(request.getId());
		dto.setIdempotencyKey(request.getIdempotencyKey());
		dto.setRequestHash(request.getRequesthash());
		dto.setResponseData(request.getResponseData());
		dto.setStatus(request.getStatus());
		dto.setCreatedAt(request.getCreatedAt());
		
		return dto;
	}
	
}
