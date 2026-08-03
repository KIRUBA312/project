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
public class RetryQueueResponse {

    private Long id;

    private String topicName;

    private String payload;

    private Integer retryAttempt;

    private LocalDateTime nextRetryTime;

    private String status;

}