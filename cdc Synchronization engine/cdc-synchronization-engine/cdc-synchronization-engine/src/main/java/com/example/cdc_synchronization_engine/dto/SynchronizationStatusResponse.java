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
public class SynchronizationStatusResponse {

    private Long id;

    private String entityName;

    private Long entityId;

    private Long kafkaOffset;

    private String elasticsearchDocumentId;

    private String status;

    private LocalDateTime lastSync;

}