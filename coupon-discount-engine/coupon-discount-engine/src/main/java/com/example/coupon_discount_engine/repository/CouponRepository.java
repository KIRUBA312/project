package com.example.coupon_discount_engine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coupon_discount_engine.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

	Optional<Coupon> findByCode(String code);
	boolean existsByCode(String code);
	boolean existsByCodeAndActiveTrue(String code);
	
}
