package com.example.cdc_synchronization_engine.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "processing_metrics")
@Getter
@Setter
public class ProcessingMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "topic_name", length = 100)
    private String topicName;

    @Column(name = "processed_records")
    private Long processedRecords;

    @Column(name = "failed_records")
    private Long failedRecords;

    @Column(name = "processing_time_ms")
    private Long processingTimeMs;

    @Column(name = "recorded_at")
    private LocalDateTime recordedAt;

    @PrePersist
    public void prePersist() {
        recordedAt = LocalDateTime.now();
    }

    // Getters and Setters
}