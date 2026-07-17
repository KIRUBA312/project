package com.example.api_monetization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.ApiAnalytics;

@Repository
public interface ApiAnalyticsRepository extends 
JpaRepository<ApiAnalytics, Long>{
	
	Optional<ApiAnalytics> findFirstByApiIdOrderByAnalyticsDateDesc(
			Long apiId);

}
