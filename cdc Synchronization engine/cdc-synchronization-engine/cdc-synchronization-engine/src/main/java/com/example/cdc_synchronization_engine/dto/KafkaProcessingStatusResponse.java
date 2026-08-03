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
public class KafkaProcessingStatusResponse {

    private Long id;

    private String topicName;

    private Integer partitionNumber;

    private Long offsetNumber;

    private String messageKey;

    private String processingStatus;

    private Integer retryCount;

    private String errorMessage;

    private LocalDateTime processedAt;

}