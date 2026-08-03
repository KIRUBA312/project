package com.example.cdc_synchronization_engine.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "elasticsearch_sync_queue")
@Getter
@Setter
public class ElasticsearchSyncQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "index_name", length = 100)
    private String indexName;

    @Column(name = "document_id", length = 100)
    private String documentId;

    @Column(name = "payload", columnDefinition = "TEXT")
    private String payload;

    @Column(name = "sync_status", length = 30)
    private String syncStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    // Getters and Setters
}