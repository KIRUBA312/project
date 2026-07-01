package com.example.coupon_discount_engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coupon_discount_engine.entity.Order;
import com.example.coupon_discount_engine.entity.OrderDiscount;

@Repository
public interface OrderDiscountRepository extends JpaRepository<OrderDiscount, Long>{
	
	List<OrderDiscount> findByOrder(Order order);

}
