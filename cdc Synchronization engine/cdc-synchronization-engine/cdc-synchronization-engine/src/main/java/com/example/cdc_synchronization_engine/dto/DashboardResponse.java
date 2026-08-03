package com.example.cdc_synchronization_engine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    // Business Data
    private Long totalOrders;

    private Long totalCustomers;

    private Long totalProducts;

    private Long totalInventory;

    private Long totalPayments;

    // CDC Statistics
    private Long totalEvents;

    private Long processedEvents;

    private Long failedEvents;

    // Retry & DLQ
    private Long retryQueueCount;

    private Long pendingRetryCount;

    private Long deadLetterCount;

    // Elasticsearch
    private Long synchronizedDocuments;

    private Long elasticPendingCount;

    // Kafka
    private Long processedKafkaEvents;

    // Analytics
    private Double successRate;

    private Double failureRate;

}