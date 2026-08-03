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
public class DeadLetterEventResponse {

    private Long id;

    private String topicName;

    private String eventKey;

    private String payload;

    private String errorMessage;

    private Integer retryCount;

    private LocalDateTime createdAt;

}