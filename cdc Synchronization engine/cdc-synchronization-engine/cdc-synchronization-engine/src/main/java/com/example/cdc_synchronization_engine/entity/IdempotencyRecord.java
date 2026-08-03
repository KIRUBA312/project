package com.example.cdc_synchronization_engine.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "idempotency_records")
@Getter
@Setter
public class IdempotencyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "event_id", unique = true, length = 150)
    private String eventId;

    @Column(name = "topic_name", length = 100)
    private String topicName;

    @Column(name = "processed")
    private Boolean processed;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    // Getters and Setters
}