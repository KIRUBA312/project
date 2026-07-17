package com.example.api_monetization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{
	
	 List<Notification> findByUserIdOrderByCreatedAtDesc(
            Long userId);

    long countByUserIdAndIsReadFalse(
            Long userId);

}
