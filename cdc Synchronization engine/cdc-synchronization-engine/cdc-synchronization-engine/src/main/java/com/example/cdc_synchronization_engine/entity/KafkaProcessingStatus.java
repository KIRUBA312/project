package com.example.cdc_synchronization_engine.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "kafka_processing_status")
@Getter
@Setter
public class KafkaProcessingStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "topic_name", length = 100)
    private String topicName;

    @Column(name = "partition_number")
    private Integer partitionNumber;

    @Column(name = "offset_number")
    private Long offsetNumber;

    @Column(name = "message_key", length = 100)
    private String messageKey;

    @Column(name = "processing_status", length = 30)
    private String processingStatus;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    // Getters and Setters
}