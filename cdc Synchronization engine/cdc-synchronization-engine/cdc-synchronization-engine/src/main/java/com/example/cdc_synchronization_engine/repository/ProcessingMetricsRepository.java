package com.example.cdc_synchronization_engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.ProcessingMetric;


@Repository
public interface ProcessingMetricsRepository
        extends JpaRepository<ProcessingMetric, Long> {

    List<ProcessingMetric>
    findByTopicName(String topicName);
}