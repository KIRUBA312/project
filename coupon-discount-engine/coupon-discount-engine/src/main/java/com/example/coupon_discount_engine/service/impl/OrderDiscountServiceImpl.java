package com.example.coupon_discount_engine.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coupon_discount_engine.dto.OrderDiscountRequestDto;
import com.example.coupon_discount_engine.dto.OrderDiscountResponseDto;
import com.example.coupon_discount_engine.entity.Coupon;
import com.example.coupon_discount_engine.entity.Order;
import com.example.coupon_discount_engine.entity.OrderDiscount;
import com.example.coupon_discount_engine.exception.ResourceNotFoundException;
import com.example.coupon_discount_engine.repository.CouponRepository;
import com.example.coupon_discount_engine.repository.OrderDiscountRepository;
import com.example.coupon_discount_engine.repository.OrderRepository;
import com.example.coupon_discount_engine.service.OrderDiscountService;
import com.example.coupon_discount_engine.util.MapperUtil;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class OrderDiscountServiceImpl implements OrderDiscountService{

	@Autowired
	private OrderDiscountRepository orderDiscountRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CouponRepository couponRepository;
	
	@Override
	public OrderDiscountResponseDto createOrderDiscount(
			@Valid OrderDiscountRequestDto request) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(request.getOrderId())
				.orElseThrow(() ->new ResourceNotFoundException(
						"Order not found with id : "+request.getOrderId()));
		Coupon coupon = couponRepository.findById(request.getCouponId())
				.orElseThrow(() ->new ResourceNotFoundException(
						"Coupon not found with id : "+request.getCouponId()));
		OrderDiscount orderDiscount = new OrderDiscount();
		orderDiscount.setOrder(order);
		orderDiscount.setCoupon(coupon);
		orderDiscount.setDiscountApplied(request.getDiscountApplied());
		
		OrderDiscount savedOrderDiscount = orderDiscountRepository.save(orderDiscount);
		
		return MapperUtil.tOrderDiscountResponse(savedOrderDiscount);
		
	}
	@Override
	public OrderDiscountResponseDto getOrderDiscountById(Long id) {
		// TODO Auto-generated method stub
		OrderDiscount orderDiscount = orderDiscountRepository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException(
						"Order Discount not found with id : "+id));
		
		return MapperUtil.tOrderDiscountResponse(orderDiscount);
	}
	@Override
	public List<OrderDiscountResponseDto> getAllOrderDiscounts() {
		// TODO Auto-generated method stub
		List<OrderDiscount> orderDiscounts = orderDiscountRepository.findAll();
		return orderDiscounts.stream().map(MapperUtil::tOrderDiscountResponse)
				.collect(Collectors.toList());
	}
	@Override
	public OrderDiscountResponseDto updateOrderDiscount(Long id, 
			@Valid OrderDiscountRequestDto request) {
		// TODO Auto-generated method stub
		OrderDiscount orderDiscount = orderDiscountRepository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException(
						"Order Discount not found with id : "+id));
		Order order = orderRepository.findById(request.getOrderId())
				.orElseThrow(() ->new ResourceNotFoundException(
						"Order not found with id : "+request.getOrderId()));
		Coupon coupon = couponRepository.findById(request.getCouponId())
				.orElseThrow(() ->new ResourceNotFoundException(
						"Coupon not found with id : "+request.getCouponId()));
		orderDiscount.setOrder(order);
		orderDiscount.setCoupon(coupon);
		orderDiscount.setDiscountApplied(request.getDiscountApplied());
		
		OrderDiscount savedOrderDiscount = orderDiscountRepository.save(orderDiscount);
		
		return MapperUtil.tOrderDiscountResponse(savedOrderDiscount);
	}
	@Override
	public void deleteOrderDiscount(Long id) {
		// TODO Auto-generated method stub
		OrderDiscount orderDiscount = orderDiscountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Order Discount not found with id : "+id));
		orderDiscountRepository.delete(orderDiscount);
		
	}
	
	
}
