package com.example.cdc_synchronization_engine.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cdc_event_log")
@Getter
@Setter
public class CDCEventLog {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "table_name", length = 100)
    private String tableName;

    @Column(name = "operation", length = 20)
    private String operation;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "payload", columnDefinition = "TEXT" )
    private String payload;

    @Column(name = "correlation_id", length = 100)
    private String correlationId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    // Getters and Setters
}