package com.example.cdc_synchronization_engine.kafka.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CDCEvent {

    private UUID eventId;

    private String entityName;

    private Long entityId;

    private String operation;

    private Object payload;

    private String correlationId;

    private LocalDateTime eventTime;

}