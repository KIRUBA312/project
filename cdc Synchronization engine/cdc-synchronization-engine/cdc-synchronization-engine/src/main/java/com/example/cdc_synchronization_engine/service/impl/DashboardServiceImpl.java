package com.example.cdc_synchronization_engine.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.dto.DashboardResponse;
import com.example.cdc_synchronization_engine.repository.CDCEventLogRepository;
import com.example.cdc_synchronization_engine.repository.CustomerRepository;
import com.example.cdc_synchronization_engine.repository.DeadLetterEventRepository;
import com.example.cdc_synchronization_engine.repository.ElasticsearchSyncQueueRepository;
import com.example.cdc_synchronization_engine.repository.InventoryRepository;
import com.example.cdc_synchronization_engine.repository.KafkaProcessingStatusRepository;
import com.example.cdc_synchronization_engine.repository.OrderRepository;
import com.example.cdc_synchronization_engine.repository.PaymentRepository;
import com.example.cdc_synchronization_engine.repository.ProductRepository;
import com.example.cdc_synchronization_engine.repository.RetryQueueRepository;
import com.example.cdc_synchronization_engine.service.DashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final InventoryRepository inventoryRepository;

    private final PaymentRepository paymentRepository;

    private final CDCEventLogRepository cdcEventLogRepository;

    private final RetryQueueRepository retryQueueRepository;

    private final DeadLetterEventRepository deadLetterEventRepository;

    private final ElasticsearchSyncQueueRepository elasticsearchSyncQueueRepository;

    private final KafkaProcessingStatusRepository kafkaProcessingStatusRepository;

    @Override
    public DashboardResponse getDashboard() {

        DashboardResponse response = new DashboardResponse();

        // ======================================================
        // BUSINESS DATA
        // ======================================================

        response.setTotalOrders(
                orderRepository.count());

        response.setTotalCustomers(
                customerRepository.count());

        response.setTotalProducts(
                productRepository.count());

        response.setTotalInventory(
                inventoryRepository.count());

        response.setTotalPayments(
                paymentRepository.count());

        // ======================================================
        // CDC
        // ======================================================

        long totalEvents =
                cdcEventLogRepository.count();

        response.setTotalEvents(totalEvents);

        // ======================================================
        // KAFKA
        // ======================================================

        long processedEvents =
                kafkaProcessingStatusRepository
                        .countByProcessingStatus("PROCESSED");

        long failedEvents =
                kafkaProcessingStatusRepository
                        .countByProcessingStatus("FAILED");

        response.setProcessedEvents(
                processedEvents);

        response.setFailedEvents(
                failedEvents);

        response.setProcessedKafkaEvents(
                processedEvents);

        // ======================================================
        // RETRY
        // ======================================================

        long retryCount =
                retryQueueRepository.count();

        long pendingRetryCount =
                retryQueueRepository
                        .countByStatus("PENDING");

        response.setRetryQueueCount(
                retryCount);

        response.setPendingRetryCount(
                pendingRetryCount);

        // ======================================================
        // DEAD LETTER
        // ======================================================

        long dlqCount =
                deadLetterEventRepository.count();

        response.setDeadLetterCount(
                dlqCount);

        // ======================================================
        // ELASTICSEARCH
        // ======================================================

        long elasticPending =
                elasticsearchSyncQueueRepository
                        .countBySyncStatus("PENDING");

        long synchronizedDocuments =
                elasticsearchSyncQueueRepository
                        .countBySyncStatus("SUCCESS");

        response.setElasticPendingCount(
                elasticPending);

        response.setSynchronizedDocuments(
                synchronizedDocuments);

        // ======================================================
        // SUCCESS / FAILURE RATE
        // ======================================================

        if (totalEvents > 0) {

            response.setSuccessRate(
                    (processedEvents * 100.0) / totalEvents);

            response.setFailureRate(
                    (failedEvents * 100.0) / totalEvents);

        } else {

            response.setSuccessRate(0.0);

            response.setFailureRate(0.0);
        }

        return response;
    }

}