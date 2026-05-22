package com.example.financialservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financialservice.entity.IdempotencyKey;

public interface IdempotencyKeyRepository extends JpaRepository<IdempotencyKey, Long>{

	Optional<IdempotencyKey> findByRequestKey(String requestKey);
	boolean existsByRequestKey(String requestKey);
	
}
