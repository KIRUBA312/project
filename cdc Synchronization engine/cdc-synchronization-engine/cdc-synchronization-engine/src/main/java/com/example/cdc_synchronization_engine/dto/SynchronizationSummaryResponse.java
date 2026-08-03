package com.example.cdc_synchronization_engine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SynchronizationSummaryResponse {

    private Long totalProcessed;

    private Long totalFailed;

    private Long pendingRetries;

    private Long deadLetterEvents;

    private Long synchronizedDocuments;

    private Double successRate;

}