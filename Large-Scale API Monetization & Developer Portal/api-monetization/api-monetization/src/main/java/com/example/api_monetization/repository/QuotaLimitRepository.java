package com.example.api_monetization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.QuotaLimit;

@Repository
public interface QuotaLimitRepository extends JpaRepository<QuotaLimit, Long>{

	Optional<QuotaLimit> findBySubscriptionId(Long subscriptionId);
}
