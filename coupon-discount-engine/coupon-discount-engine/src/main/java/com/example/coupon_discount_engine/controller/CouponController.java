package com.example.coupon_discount_engine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coupon_discount_engine.dto.CouponRequestDto;
import com.example.coupon_discount_engine.dto.CouponResponseDto;
import com.example.coupon_discount_engine.service.CouponService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/coupons")
@Validated
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	@PostMapping
	public ResponseEntity<CouponResponseDto> createCoupon(@Valid
			@RequestBody CouponRequestDto request){
		return new ResponseEntity<>(couponService.createCoupon(request),
				HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<CouponResponseDto> getCouponById(
			@PathVariable Long id){
		return ResponseEntity.ok(couponService.getCouponById(id));
	}
	@GetMapping
	public ResponseEntity<List<CouponResponseDto>> getAllCoupons(){
		return ResponseEntity.ok(couponService.getAllcoupons());
	}
	@PutMapping("/{id}")
	public ResponseEntity<CouponResponseDto> updateCoupon(
			@PathVariable Long id,@Valid @RequestBody CouponRequestDto request){
		return ResponseEntity.ok(couponService.updateCoupon(id,request));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCoupon(@PathVariable Long id){
		couponService.deleteCoupon(id);
		return ResponseEntity.ok("coupon deleted successfully");
	}
	
}
