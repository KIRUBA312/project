package com.example.cdc_synchronization_engine.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CDCEventLogResponse {

    private UUID id;

    private String tableName;

    private String operation;

    private Long entityId;

    private String payload;

    private String correlationId;

    private LocalDateTime createdAt;

}