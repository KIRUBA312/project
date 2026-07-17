package com.example.api_monetization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.DeveloperSubscription;
import com.example.api_monetization.enums.SubscriptionStatus;
@Repository
public interface DeveloperSubscriptionRepository extends JpaRepository<DeveloperSubscription, Long> {

    List<DeveloperSubscription> findByDeveloperId(Long developerId);
    boolean existsByApplicationId(Long applicationId);

    List<DeveloperSubscription> findBySubscriptionStatus(SubscriptionStatus status);

}