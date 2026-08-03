package com.example.cdc_synchronization_engine.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

import com.example.cdc_synchronization_engine.entity.AuditLog;
import com.example.cdc_synchronization_engine.entity.CDCEventLog;
import com.example.cdc_synchronization_engine.entity.DeadLetterEvent;
import com.example.cdc_synchronization_engine.entity.ElasticsearchSyncQueue;
import com.example.cdc_synchronization_engine.entity.IdempotencyRecord;
import com.example.cdc_synchronization_engine.entity.KafkaProcessingStatus;
import com.example.cdc_synchronization_engine.entity.ProcessingMetric;
import com.example.cdc_synchronization_engine.entity.RetryQueue;
import com.example.cdc_synchronization_engine.entity.SynchronizationStatus;

import com.example.cdc_synchronization_engine.exception.ErrorCode;
import com.example.cdc_synchronization_engine.exception.ResourceNotFoundException;

import com.example.cdc_synchronization_engine.mapper.AuditLogMapper;
import com.example.cdc_synchronization_engine.mapper.CDCEventLogMapper;
import com.example.cdc_synchronization_engine.mapper.DeadLetterEventMapper;
import com.example.cdc_synchronization_engine.mapper.ElasticsearchSyncQueueMapper;
import com.example.cdc_synchronization_engine.mapper.IdempotencyRecordMapper;
import com.example.cdc_synchronization_engine.mapper.KafkaProcessingStatusMapper;
import com.example.cdc_synchronization_engine.mapper.ProcessingMetricMapper;
import com.example.cdc_synchronization_engine.mapper.RetryQueueMapper;
import com.example.cdc_synchronization_engine.mapper.SynchronizationStatusMapper;

import com.example.cdc_synchronization_engine.repository.AuditLogRepository;
import com.example.cdc_synchronization_engine.repository.CDCEventLogRepository;
import com.example.cdc_synchronization_engine.repository.DeadLetterEventRepository;
import com.example.cdc_synchronization_engine.repository.ElasticsearchSyncQueueRepository;
import com.example.cdc_synchronization_engine.repository.IdempotencyRecordRepository;
import com.example.cdc_synchronization_engine.repository.KafkaProcessingStatusRepository;
import com.example.cdc_synchronization_engine.repository.ProcessingMetricsRepository;
import com.example.cdc_synchronization_engine.repository.RetryQueueRepository;
import com.example.cdc_synchronization_engine.repository.SynchronizationStatusRepository;

import com.example.cdc_synchronization_engine.service.DeadLetterRetryService;
import com.example.cdc_synchronization_engine.service.MonitoringService;

@Service
@Transactional(readOnly = true)
public class MonitoringServiceImpl implements MonitoringService {

    private final CDCEventLogRepository cdcEventLogRepository;
    private final SynchronizationStatusRepository synchronizationStatusRepository;
    private final KafkaProcessingStatusRepository kafkaProcessingStatusRepository;
    private final AuditLogRepository auditLogRepository;
    private final DeadLetterEventRepository deadLetterEventRepository;
    private final ProcessingMetricsRepository processingMetricRepository;
    private final RetryQueueRepository retryQueueRepository;
    private final ElasticsearchSyncQueueRepository elasticsearchSyncQueueRepository;
    private final IdempotencyRecordRepository idempotencyRecordRepository;

    private final CDCEventLogMapper cdcEventLogMapper;
    private final SynchronizationStatusMapper synchronizationStatusMapper;
    private final KafkaProcessingStatusMapper kafkaProcessingStatusMapper;
    private final AuditLogMapper auditLogMapper;
    private final DeadLetterEventMapper deadLetterEventMapper;
    private final ProcessingMetricMapper processingMetricMapper;
    private final RetryQueueMapper retryQueueMapper;
    private final ElasticsearchSyncQueueMapper elasticsearchSyncQueueMapper;
    private final IdempotencyRecordMapper idempotencyRecordMapper;

    private final DeadLetterRetryService deadLetterRetryService;

    public MonitoringServiceImpl(
            CDCEventLogRepository cdcEventLogRepository,
            SynchronizationStatusRepository synchronizationStatusRepository,
            KafkaProcessingStatusRepository kafkaProcessingStatusRepository,
            AuditLogRepository auditLogRepository,
            DeadLetterEventRepository deadLetterEventRepository,
            ProcessingMetricsRepository processingMetricRepository,
            RetryQueueRepository retryQueueRepository,
            ElasticsearchSyncQueueRepository elasticsearchSyncQueueRepository,
            IdempotencyRecordRepository idempotencyRecordRepository,

            CDCEventLogMapper cdcEventLogMapper,
            SynchronizationStatusMapper synchronizationStatusMapper,
            KafkaProcessingStatusMapper kafkaProcessingStatusMapper,
            AuditLogMapper auditLogMapper,
            DeadLetterEventMapper deadLetterEventMapper,
            ProcessingMetricMapper processingMetricMapper,
            RetryQueueMapper retryQueueMapper,
            ElasticsearchSyncQueueMapper elasticsearchSyncQueueMapper,
            IdempotencyRecordMapper idempotencyRecordMapper,

            DeadLetterRetryService deadLetterRetryService) {

        this.cdcEventLogRepository = cdcEventLogRepository;
        this.synchronizationStatusRepository = synchronizationStatusRepository;
        this.kafkaProcessingStatusRepository = kafkaProcessingStatusRepository;
        this.auditLogRepository = auditLogRepository;
        this.deadLetterEventRepository = deadLetterEventRepository;
        this.processingMetricRepository = processingMetricRepository;
        this.retryQueueRepository = retryQueueRepository;
        this.elasticsearchSyncQueueRepository = elasticsearchSyncQueueRepository;
        this.idempotencyRecordRepository = idempotencyRecordRepository;

        this.cdcEventLogMapper = cdcEventLogMapper;
        this.synchronizationStatusMapper = synchronizationStatusMapper;
        this.kafkaProcessingStatusMapper = kafkaProcessingStatusMapper;
        this.auditLogMapper = auditLogMapper;
        this.deadLetterEventMapper = deadLetterEventMapper;
        this.processingMetricMapper = processingMetricMapper;
        this.retryQueueMapper = retryQueueMapper;
        this.elasticsearchSyncQueueMapper = elasticsearchSyncQueueMapper;
        this.idempotencyRecordMapper = idempotencyRecordMapper;

        this.deadLetterRetryService = deadLetterRetryService;
    }

    // ============================================================
    // CDC EVENTS
    // ============================================================

    @Override
    public List<CDCEventLogResponse> getCDCEvents() {

        return cdcEventLogRepository.findAll()
                .stream()
                .map(cdcEventLogMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ============================================================
    // SYNCHRONIZATION STATUS
    // ============================================================

    @Override
    public List<SynchronizationStatusResponse> getSynchronizationStatus() {

        return synchronizationStatusRepository.findAll()
                .stream()
                .map(synchronizationStatusMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ============================================================
    // KAFKA PROCESSING STATUS
    // ============================================================

    @Override
    public List<KafkaProcessingStatusResponse> getKafkaProcessingStatus() {

        return kafkaProcessingStatusRepository.findAll()
                .stream()
                .map(kafkaProcessingStatusMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ============================================================
    // AUDIT LOGS
    // ============================================================

    @Override
    public List<AuditLogResponse> getAuditLogs() {

        return auditLogRepository.findAll()
                .stream()
                .map(auditLogMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ============================================================
    // DEAD LETTER EVENTS
    // ============================================================

    @Override
    public List<DeadLetterEventResponse> getDeadLetterEvents() {

        return deadLetterEventRepository.findAll()
                .stream()
                .map(deadLetterEventMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ============================================================
    // RETRY DEAD LETTER EVENT
    // ============================================================

    @Override
    @Transactional
    public void retryDeadLetterEvent(Long id) {

        deadLetterRetryService.retryDeadLetterEvent(id);
    }

    // ============================================================
    // PROCESSING METRICS
    // ============================================================

    @Override
    public List<ProcessingMetricResponse> getProcessingMetrics() {

        return processingMetricRepository.findAll()
                .stream()
                .map(processingMetricMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ============================================================
    // RETRY QUEUE
    // ============================================================

    @Override
    public List<RetryQueueResponse> getRetryQueue() {

        return retryQueueRepository.findAll()
                .stream()
                .map(retryQueueMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ============================================================
    // ELASTICSEARCH SYNC QUEUE
    // ============================================================

    @Override
    public List<ElasticsearchSyncQueueResponse> getElasticsearchSyncQueue() {

        return elasticsearchSyncQueueRepository.findAll()
                .stream()
                .map(elasticsearchSyncQueueMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ============================================================
    // IDEMPOTENCY RECORDS
    // ============================================================

    @Override
    public List<IdempotencyRecordResponse> getIdempotencyRecords() {

        return idempotencyRecordRepository.findAll()
                .stream()
                .map(idempotencyRecordMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ============================================================
    // APPLICATION STATUS
    // ============================================================

    @Override
    public ApiStatusResponse getApplicationStatus() {

        ApiStatusResponse response = new ApiStatusResponse();

        response.setServiceName("cdc-synchronization-engine");

        response.setApplicationStatus("UP");

        response.setDatabaseStatus(checkDatabaseStatus());

        
        response.setKafkaStatus("UNKNOWN");
        response.setRedisStatus("UNKNOWN");
        response.setElasticsearchStatus("UNKNOWN");

        response.setServerTime(LocalDateTime.now());

        return response;
    }

   
    @Override
    public DashboardResponse getDashboard() {

        DashboardResponse response = new DashboardResponse();

        long totalEvents =
                cdcEventLogRepository.count();

        long processedEvents =
                kafkaProcessingStatusRepository
                        .countByProcessingStatus("PROCESSED");

        long failedEvents =
                kafkaProcessingStatusRepository
                        .countByProcessingStatus("FAILED");

        long retryQueueCount =
                retryQueueRepository
                        .countByStatus("PENDING");

        long deadLetterCount =
                deadLetterEventRepository.count();

        long synchronizedDocuments =
                synchronizationStatusRepository
                        .countByStatus("SUCCESS");

        double successRate = 0.0;
        double failureRate = 0.0;

        if (totalEvents > 0) {

            successRate =
                    (processedEvents * 100.0) / totalEvents;

            failureRate =
                    (failedEvents * 100.0) / totalEvents;
        }

        // Business counts
        response.setTotalOrders(0L);
        response.setTotalCustomers(0L);
        response.setTotalProducts(0L);
        response.setTotalInventory(0L);
        response.setTotalPayments(0L);

        // CDC
        response.setTotalEvents(totalEvents);
        response.setProcessedEvents(processedEvents);
        response.setFailedEvents(failedEvents);

        // Retry
        response.setRetryQueueCount(retryQueueCount);
        response.setPendingRetryCount(retryQueueCount);

        // DLQ
        response.setDeadLetterCount(deadLetterCount);

        // Elasticsearch
        response.setSynchronizedDocuments(synchronizedDocuments);
        response.setElasticPendingCount(0L);

        // Kafka
        response.setProcessedKafkaEvents(processedEvents);

        // Statistics
        response.setSuccessRate(successRate);
        response.setFailureRate(failureRate);

        return response;
    }

    @Override
    public SynchronizationSummaryResponse getSynchronizationSummary() {

        long totalProcessed =
                kafkaProcessingStatusRepository
                        .countByProcessingStatus("PROCESSED");

        long totalFailed =
                kafkaProcessingStatusRepository
                        .countByProcessingStatus("FAILED");

        long pendingRetries =
                retryQueueRepository
                        .countByStatus("PENDING");

        long deadLetterEvents =
                deadLetterEventRepository.count();

        long synchronizedDocuments =
                synchronizationStatusRepository
                        .countByStatus("SUCCESS");

        long total =
                totalProcessed + totalFailed;

        double successRate = 0.0;

        if (total > 0) {

            successRate =
                    ((double) totalProcessed / total) * 100;
        }

        return new SynchronizationSummaryResponse(
                totalProcessed,
                totalFailed,
                pendingRetries,
                deadLetterEvents,
                synchronizedDocuments,
                successRate
        );
    }

    @Override
    public List<TopicStatisticsResponse> getTopicStatistics() {

        return kafkaProcessingStatusRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        KafkaProcessingStatus::getTopicName))
                .entrySet()
                .stream()
                .map(entry -> {

                    String topicName = entry.getKey();

                    List<KafkaProcessingStatus> records =
                            entry.getValue();

                    long processedRecords =
                            records.stream()
                                    .filter(record ->
                                            "PROCESSED".equalsIgnoreCase(
                                                    record.getProcessingStatus()))
                                    .count();

                    long failedRecords =
                            records.stream()
                                    .filter(record ->
                                            "FAILED".equalsIgnoreCase(
                                                    record.getProcessingStatus()))
                                    .count();

                    long retryCount =
                            records.stream()
                                    .mapToLong(record ->
                                            record.getRetryCount() == null
                                                    ? 0
                                                    : record.getRetryCount())
                                    .sum();

                    long deadLetterCount =
                            deadLetterEventRepository
                                    .findByTopicName(topicName)
                                    .size();

                    double averageProcessingTime = 0.0;

                    List<ProcessingMetric> metrics =
                            processingMetricRepository
                                    .findByTopicName(topicName);

                    if (!metrics.isEmpty()) {

                        averageProcessingTime =
                                metrics.stream()
                                        .filter(metric ->
                                                metric.getProcessingTimeMs() != null)
                                        .mapToLong(
                                                ProcessingMetric::getProcessingTimeMs)
                                        .average()
                                        .orElse(0.0);
                    }

                    return new TopicStatisticsResponse(
                            topicName,
                            processedRecords,
                            failedRecords,
                            retryCount,
                            deadLetterCount,
                            averageProcessingTime
                    );
                })
                .collect(Collectors.toList());
    }

   
    private String checkDatabaseStatus() {

        try {

            cdcEventLogRepository.count();

            return "UP";

        } catch (Exception ex) {

            return "DOWN";
        }
    }
}