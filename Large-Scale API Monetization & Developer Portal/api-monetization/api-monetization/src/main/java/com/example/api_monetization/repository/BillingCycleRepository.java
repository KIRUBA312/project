package com.example.api_monetization.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.BillingCycleEntity;

@Repository
public interface BillingCycleRepository extends JpaRepository<BillingCycleEntity, Long>{

	@Query("""
			SELECT b
			FROM BillingCycleEntity b
			WHERE :today BETWEEN b.startDate AND b.endDate
			""")
	Optional<BillingCycleEntity> findCurrentBillingCycle(
			LocalDate today);
	
}
