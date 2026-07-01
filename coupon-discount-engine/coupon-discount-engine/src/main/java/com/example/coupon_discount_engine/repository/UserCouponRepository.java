package com.example.coupon_discount_engine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coupon_discount_engine.entity.Coupon;
import com.example.coupon_discount_engine.entity.User;
import com.example.coupon_discount_engine.entity.UserCoupon;

@Repository
public interface UserCouponRepository extends JpaRepository<UserCoupon, Long>{
	
	Optional<UserCoupon> findByUserAndCoupon(User user,Coupon coupon);
	List<UserCoupon> findByUser(User user);
	List<UserCoupon> findByCoupon(Coupon coupon);
	boolean existsByUserAndCoupon(User user,Coupon coupon);

}
