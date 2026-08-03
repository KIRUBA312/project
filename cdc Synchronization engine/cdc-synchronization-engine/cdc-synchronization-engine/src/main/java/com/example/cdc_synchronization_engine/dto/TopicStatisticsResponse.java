package com.example.cdc_synchronization_engine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicStatisticsResponse {

    private String topicName;

    private Long processedRecords;

    private Long failedRecords;

    private Long retryCount;

    private Long deadLetterCount;

    private Double averageProcessingTime;

}