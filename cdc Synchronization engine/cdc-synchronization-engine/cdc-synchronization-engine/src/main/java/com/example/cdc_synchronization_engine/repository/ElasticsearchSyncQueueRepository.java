package com.example.cdc_synchronization_engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.ElasticsearchSyncQueue;

@Repository
public interface ElasticsearchSyncQueueRepository
        extends JpaRepository<ElasticsearchSyncQueue, Long> {

    List<ElasticsearchSyncQueue>
    findBySyncStatus(String syncStatus);

	long countBySyncStatus(String status);
}