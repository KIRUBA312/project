package com.example.cdc_synchronization_engine.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessingMetricResponse {

    private Long id;

    private String topicName;

    private Long processedRecords;

    private Long failedRecords;

    private Long processingTimeMs;

    private LocalDateTime recordedAt;

}