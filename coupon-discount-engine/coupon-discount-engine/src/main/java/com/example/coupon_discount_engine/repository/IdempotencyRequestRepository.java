package com.example.coupon_discount_engine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coupon_discount_engine.entity.IdempotencyRequest;

@Repository
public interface IdempotencyRequestRepository extends JpaRepository<IdempotencyRequest, Long>{
	
	Optional<IdempotencyRequest> findByIdempotencyKey(String idempotencyKey);
	boolean existsByIdempotencyKey(String idempotencyKey);

}
