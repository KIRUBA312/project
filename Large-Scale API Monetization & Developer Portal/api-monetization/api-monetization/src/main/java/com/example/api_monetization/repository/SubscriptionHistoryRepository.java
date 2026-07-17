package com.example.api_monetization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.SubscriptionHistory;

@Repository
public interface SubscriptionHistoryRepository extends JpaRepository<SubscriptionHistory, Long>{
	List<SubscriptionHistory> findBySubscriptionId(Long subscriptionId);

}
