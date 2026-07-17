package com.example.api_monetization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.DeveloperAnalytics;

@Repository
public interface DeveloperAnalyticsRepository extends JpaRepository<DeveloperAnalytics, Long>{

	Optional<DeveloperAnalytics>
	findFirstByDeveloperIdOrderByAnalyticsDateDesc(Long developerId);
}
