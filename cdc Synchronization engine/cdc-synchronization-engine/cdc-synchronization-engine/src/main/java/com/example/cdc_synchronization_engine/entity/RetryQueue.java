package com.example.cdc_synchronization_engine.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "retry_queue")
@Getter
@Setter
public class RetryQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "topic_name", length = 100)
    private String topicName;

    @Column(name = "payload", columnDefinition = "jsonb")
    private String payload;

    @Column(name = "retry_attempt")
    private Integer retryAttempt;

    @Column(name = "next_retry_time")
    private LocalDateTime nextRetryTime;

    @Column(name = "status", length = 30)
    private String status;

	

    // Getters and Setters
}