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
public class AuditLogResponse {

    private Long id;

    private String username;

    private String action;

    private String entityName;

    private Long entityId;

    private String correlationId;

    private String ipAddress;

    private LocalDateTime createdAt;

}