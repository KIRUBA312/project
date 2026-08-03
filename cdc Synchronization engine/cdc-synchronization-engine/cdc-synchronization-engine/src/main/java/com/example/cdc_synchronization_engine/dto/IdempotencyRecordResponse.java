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
public class IdempotencyRecordResponse {

    private Long id;

    private String eventId;

    private String topicName;

    private Boolean processed;

    private LocalDateTime processedAt;

}