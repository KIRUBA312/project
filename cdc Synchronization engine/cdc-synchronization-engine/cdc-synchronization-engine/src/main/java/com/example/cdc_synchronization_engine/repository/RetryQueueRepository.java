package com.example.cdc_synchronization_engine.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.RetryQueue;

@Repository
public interface RetryQueueRepository
        extends JpaRepository<RetryQueue, Long> {

    List<RetryQueue>
    findByStatus(String status); 
    long countByStatus(String status);

    List<RetryQueue>
    findByNextRetryTimeBefore(
            LocalDateTime time
    );
	List<RetryQueue> findByStatusAndNextRetryTimeLessThanEqual(
			String string, LocalDateTime nextRetryTime);
}