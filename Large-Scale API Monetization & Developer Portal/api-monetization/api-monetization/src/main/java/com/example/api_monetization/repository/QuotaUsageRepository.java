package com.example.api_monetization.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.QuotaUsage;
@Repository
public interface QuotaUsageRepository extends JpaRepository<QuotaUsage, Long> {

    Optional<QuotaUsage> findBySubscriptionIdAndUsageDate(Long subscriptionId,
                                                          LocalDate usageDate);

    Optional<QuotaUsage> findFirstBySubscriptionIdOrderByUsageDateDesc(
            Long subscriptionId);
}