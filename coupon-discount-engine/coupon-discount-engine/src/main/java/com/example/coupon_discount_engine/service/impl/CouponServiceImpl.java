package com.example.coupon_discount_engine.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coupon_discount_engine.dto.CouponRequestDto;
import com.example.coupon_discount_engine.dto.CouponResponseDto;
import com.example.coupon_discount_engine.entity.Coupon;
import com.example.coupon_discount_engine.exception.DuplicateCouponException;
import com.example.coupon_discount_engine.exception.ResourceNotFoundException;
import com.example.coupon_discount_engine.repository.CouponRepository;
import com.example.coupon_discount_engine.service.CouponService;
import com.example.coupon_discount_engine.strategy.DiscountStrategyFactory;
import com.example.coupon_discount_engine.util.MapperUtil;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class CouponServiceImpl implements CouponService{
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private DiscountStrategyFactory strategyFactory;

	@Override
	public CouponResponseDto createCoupon(@Valid CouponRequestDto request) {
		// TODO Auto-generated method stub
		if(couponRepository.existsByCode(request.getCode())) {
			throw new DuplicateCouponException(
					"Coupon already exists with code : "+request.getCode());
		}
		Coupon coupon = new Coupon();
		coupon.setCode(request.getCode());
		coupon.setName(request.getCouponName());
		coupon.setType(request.getCouponType());
		coupon.setDiscountValue(request.getDiscountValue());
		coupon.setMinimumOrderAmount(request.getMinimumOrderAmount());
		coupon.setMaximumDiscount(request.getMaximumDiscount());
		coupon.setProductCategory(request.getProductCategory());
		coupon.setExpiryDate(request.getExpiryDate());
		coupon.setUsageLimit(request.getUsageLimit());
		
		if(request.getReusable() == null) {
			coupon.setReusable(false);
		}else {
			coupon.setReusable(request.getReusable());
		}
		if (request.getActive()==null) {
			coupon.setActive(true);
		}else {
			coupon.setActive(request.getActive());
		}
		
		coupon.setUsedCount(0);
		coupon.setCreated_At(LocalDateTime.now());
		coupon.setUpdated_At(LocalDateTime.now());
		
		Coupon savedCoupon = couponRepository.save(coupon);
		
		return MapperUtil.toCouponResponse(savedCoupon);
	}

	@Override
	public CouponResponseDto getCouponById(Long id) {
		// TODO Auto-generated method stub
		Coupon coupon = couponRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Coupon not found with id : "+id));
		return MapperUtil.toCouponResponse(coupon);
	}

	@Override
	public List<CouponResponseDto> getAllcoupons() {
		// TODO Auto-generated method stub
		List<Coupon> coupons = couponRepository.findAll();
		return coupons.stream().map(MapperUtil::toCouponResponse)
				.collect(Collectors.toList());
	}

	@Override
	public CouponResponseDto updateCoupon(Long id, @Valid CouponRequestDto request) {
		// TODO Auto-generated method stub
		Coupon coupon = couponRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("coupon not found with id :"+id));
		if (!coupon.getCode().equals(request.getCode()) 
			&& couponRepository.existsByCode(request.getCode())) {
			
			throw new DuplicateCouponException("Coupon Code already exists : "+request.getCode());
		}
		coupon.setCode(request.getCode());
		coupon.setName(request.getCouponName());
		coupon.setType(request.getCouponType());
		coupon.setDiscountValue(request.getDiscountValue());
		coupon.setMinimumOrderAmount(request.getMinimumOrderAmount());
		coupon.setMaximumDiscount(request.getMaximumDiscount());
		coupon.setProductCategory(request.getProductCategory());
		coupon.setExpiryDate(request.getExpiryDate());
		coupon.setUsageLimit(request.getUsageLimit());
		coupon.setReusable(request.getReusable());
//		coupon.setActive(true);
		coupon.setActive(request.getActive());
		
//		coupon.setUsedCount(0);
		coupon.setUpdated_At(LocalDateTime.now());
		
		Coupon updatedCoupon = couponRepository.save(coupon);
		return MapperUtil.toCouponResponse(updatedCoupon);
	}

	@Override
	public void deleteCoupon(Long id) {
		// TODO Auto-generated method stub
		Coupon coupon = couponRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Coupon not found with id : "+id));
		couponRepository.delete(coupon);
		
	}
	
	public Coupon validateCoupon(Coupon coupon,
            String productCategory,
            java.math.BigDecimal orderAmount) {

		if (coupon == null) {
			throw new ResourceNotFoundException("Coupon not found.");
		}
			
		if (!Boolean.TRUE.equals(coupon.getActive())) {
			throw new RuntimeException("Coupon is inactive.");
		}
			
		if (coupon.getExpiryDate().isBefore(LocalDate.now())) {
			throw new RuntimeException("Coupon has expired.");
		}
			
		if (coupon.getUsedCount() >= coupon.getUsageLimit()) {
			throw new RuntimeException("Coupon usage limit exceeded.");
		}
			
		if (coupon.getMinimumOrderAmount() != null
			&& orderAmount.compareTo(coupon.getMinimumOrderAmount()) < 0) {
			
			throw new RuntimeException(
			"Minimum order amount should be "
			       + coupon.getMinimumOrderAmount());
		}
			
		if (coupon.getProductCategory() != null
			&& !coupon.getProductCategory().equalsIgnoreCase(productCategory)) {
			
			throw new RuntimeException(
			"Coupon not applicable for this product category.");
		}
			
		return coupon;
	}
	
	public BigDecimal calculationDiscount(Coupon coupon, BigDecimal orderAmount) {
		return strategyFactory.getStrategy(coupon.getType())
				.applyDiscount(coupon, orderAmount);
	}
	
	public void increaseCouponUsage(Coupon coupon) {
		coupon.setUsedCount(coupon.getUsedCount()+1);
		couponRepository.save(coupon);
	}
	
	public Coupon findCouponByCode(String code) {
		return couponRepository.findByCode(code).orElseThrow(() ->
		new ResourceNotFoundException("Coupon not found with code : "+code));
	}
	
	public boolean isCouponExpired(Coupon coupon) {
		return coupon.getExpiryDate().isBefore(LocalDate.now());
	}
	
	public boolean isCouponAvailable(Coupon coupon) {
		return coupon.getUsedCount()<coupon.getUsageLimit();
	}
	
	public void deactiveCoupon(Long id) {
		Coupon coupon = couponRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Coupon not found with id : "+id));
		
		coupon.setActive(false);
		couponRepository.save(coupon);
	}
	public void activateCoupon(Long id) {
		Coupon coupon = couponRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Coupon not found with id : "+id));
		
		coupon.setActive(true);
		couponRepository.save(coupon);
	}

}
