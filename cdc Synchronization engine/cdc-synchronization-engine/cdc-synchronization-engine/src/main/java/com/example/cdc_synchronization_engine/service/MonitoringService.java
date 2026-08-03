package com.example.cdc_synchronization_engine.service;

import java.util.List;

import com.example.cdc_synchronization_engine.dto.ApiStatusResponse;
import com.example.cdc_synchronization_engine.dto.AuditLogResponse;
import com.example.cdc_synchronization_engine.dto.CDCEventLogResponse;
import com.example.cdc_synchronization_engine.dto.DashboardResponse;
import com.example.cdc_synchronization_engine.dto.DeadLetterEventResponse;
import com.example.cdc_synchronization_engine.dto.ElasticsearchSyncQueueResponse;
import com.example.cdc_synchronization_engine.dto.IdempotencyRecordResponse;
import com.example.cdc_synchronization_engine.dto.KafkaProcessingStatusResponse;
import com.example.cdc_synchronization_engine.dto.ProcessingMetricResponse;
import com.example.cdc_synchronization_engine.dto.RetryQueueResponse;
import com.example.cdc_synchronization_engine.dto.SynchronizationStatusResponse;
import com.example.cdc_synchronization_engine.dto.SynchronizationSummaryResponse;
import com.example.cdc_synchronization_engine.dto.TopicStatisticsResponse;

public interface MonitoringService {

    List<CDCEventLogResponse> getCDCEvents();

    List<SynchronizationStatusResponse> getSynchronizationStatus();

    List<KafkaProcessingStatusResponse> getKafkaProcessingStatus();

    List<AuditLogResponse> getAuditLogs();

    List<DeadLetterEventResponse> getDeadLetterEvents();

    void retryDeadLetterEvent(Long id);

    List<ProcessingMetricResponse> getProcessingMetrics();

    List<RetryQueueResponse> getRetryQueue();

    List<ElasticsearchSyncQueueResponse> getElasticsearchSyncQueue();

    List<IdempotencyRecordResponse> getIdempotencyRecords();

    List<TopicStatisticsResponse> getTopicStatistics();

    ApiStatusResponse getApplicationStatus();

    DashboardResponse getDashboard();

    SynchronizationSummaryResponse getSynchronizationSummary();
}