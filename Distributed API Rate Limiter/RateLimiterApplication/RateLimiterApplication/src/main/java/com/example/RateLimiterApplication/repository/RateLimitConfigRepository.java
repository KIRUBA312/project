package com.example.RateLimiterApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RateLimiterApplication.entity.RateLimitingConfig;

@Repository
public interface RateLimitConfigRepository extends JpaRepository<RateLimitingConfig, Long>{
	
	Optional<RateLimitingConfig>findByEndpoint(String endpoint);
	Optional<RateLimitingConfig>findByApiName(String apiName);

}

