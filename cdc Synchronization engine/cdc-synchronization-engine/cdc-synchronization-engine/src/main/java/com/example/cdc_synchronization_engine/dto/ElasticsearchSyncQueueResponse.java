package com.example.cdc_synchronization_engine.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ElasticsearchSyncQueueResponse {

    private Long id;

    private String indexName;

    private String documentId;

    private String payload;

    private String syncStatus;

    private LocalDateTime createdAt;

}