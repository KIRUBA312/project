package com.example.coupon_discount_engine.service;

import java.util.List;

import com.example.coupon_discount_engine.dto.OrderDiscountRequestDto;
import com.example.coupon_discount_engine.dto.OrderDiscountResponseDto;

import jakarta.validation.Valid;

public interface OrderDiscountService {

	OrderDiscountResponseDto createOrderDiscount(@Valid OrderDiscountRequestDto request);

	OrderDiscountResponseDto getOrderDiscountById(Long id);

	List<OrderDiscountResponseDto> getAllOrderDiscounts();

	OrderDiscountResponseDto updateOrderDiscount(Long id, @Valid OrderDiscountRequestDto request);

	void deleteOrderDiscount(Long id);

}
