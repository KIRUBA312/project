package com.example.cdc_synchronization_engine.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "synchronization_status")
@Getter
@Setter
public class SynchronizationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "entity_name", length = 100)
    private String entityName;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "kafka_offset")
    private Long kafkaOffset;

    @Column(name = "elasticsearch_document_id", length = 100)
    private String elasticsearchDocumentId;

    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "last_sync")
    private LocalDateTime lastSync;

    // Getters and Setters
}