package com.example.coupon_discount_engine.service;

import java.util.List;

import com.example.coupon_discount_engine.dto.CouponRequestDto;
import com.example.coupon_discount_engine.dto.CouponResponseDto;

import jakarta.validation.Valid;

public interface CouponService {

	CouponResponseDto createCoupon(@Valid CouponRequestDto request);

	CouponResponseDto getCouponById(Long id);

	List<CouponResponseDto> getAllcoupons();

	CouponResponseDto updateCoupon(Long id, @Valid CouponRequestDto request);

	void deleteCoupon(Long id);

}
