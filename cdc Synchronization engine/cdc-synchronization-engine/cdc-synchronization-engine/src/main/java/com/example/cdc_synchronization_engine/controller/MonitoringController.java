package com.example.cdc_synchronization_engine.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cdc_synchronization_engine.dto.ApiResponse;
import com.example.cdc_synchronization_engine.dto.AuditLogResponse;
import com.example.cdc_synchronization_engine.dto.CDCEventLogResponse;
import com.example.cdc_synchronization_engine.dto.DeadLetterEventResponse;
import com.example.cdc_synchronization_engine.dto.ElasticsearchSyncQueueResponse;
import com.example.cdc_synchronization_engine.dto.KafkaProcessingStatusResponse;
import com.example.cdc_synchronization_engine.dto.ProcessingMetricResponse;
import com.example.cdc_synchronization_engine.dto.RetryQueueResponse;
import com.example.cdc_synchronization_engine.dto.SynchronizationStatusResponse;

import com.example.cdc_synchronization_engine.service.MonitoringService;

@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController {

    private final MonitoringService monitoringService;

    public MonitoringController(
            MonitoringService monitoringService) {

        this.monitoringService = monitoringService;
    }

    @GetMapping("/cdc-events")
    public ResponseEntity<ApiResponse<List<CDCEventLogResponse>>>
    getCDCEvents() {

        List<CDCEventLogResponse> data =
                monitoringService.getCDCEvents();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "CDC events retrieved successfully",
                        data
                )
        );
    }

    @GetMapping("/synchronization-status")
    public ResponseEntity<
            ApiResponse<List<SynchronizationStatusResponse>>>
    getSynchronizationStatus() {

        List<SynchronizationStatusResponse> data =
                monitoringService.getSynchronizationStatus();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Synchronization status retrieved successfully",
                        data
                )
        );
    }

    @GetMapping("/kafka-processing-status")
    public ResponseEntity<
            ApiResponse<List<KafkaProcessingStatusResponse>>>
    getKafkaProcessingStatus() {

        List<KafkaProcessingStatusResponse> data =
                monitoringService.getKafkaProcessingStatus();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Kafka processing status retrieved successfully",
                        data
                )
        );
    }

    @GetMapping("/audit-logs")
    public ResponseEntity<
            ApiResponse<List<AuditLogResponse>>>
    getAuditLogs() {

        List<AuditLogResponse> data =
                monitoringService.getAuditLogs();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Audit logs retrieved successfully",
                        data
                )
        );
    }

    @GetMapping("/dead-letter")
    public ResponseEntity<
            ApiResponse<List<DeadLetterEventResponse>>>
    getDeadLetterEvents() {

        List<DeadLetterEventResponse> data =
                monitoringService.getDeadLetterEvents();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Dead letter events retrieved successfully",
                        data
                )
        );
    }

    @PostMapping("/dead-letter/{id}/retry")
    public ResponseEntity<ApiResponse<Void>>
    retryDeadLetterEvent(
            @PathVariable Long id) {

        monitoringService.retryDeadLetterEvent(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Dead letter event retry initiated successfully",
                        null
                )
        );
    }

    @GetMapping("/processing-metrics")
    public ResponseEntity<
            ApiResponse<List<ProcessingMetricResponse>>>
    getProcessingMetrics() {

        List<ProcessingMetricResponse> data =
                monitoringService.getProcessingMetrics();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Processing metrics retrieved successfully",
                        data
                )
        );
    }

    @GetMapping("/retry-queue")
    public ResponseEntity<
            ApiResponse<List<RetryQueueResponse>>>
    getRetryQueue() {

        List<RetryQueueResponse> data =
                monitoringService.getRetryQueue();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Retry queue retrieved successfully",
                        data
                )
        );
    }

    @GetMapping("/elasticsearch-sync-queue")
    public ResponseEntity<
            ApiResponse<List<ElasticsearchSyncQueueResponse>>>
    getElasticsearchSyncQueue() {

        List<ElasticsearchSyncQueueResponse> data =
                monitoringService.getElasticsearchSyncQueue();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Elasticsearch sync queue retrieved successfully",
                        data
                )
        );
    }
}