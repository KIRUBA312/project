package com.example.cdc_synchronization_engine.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.cdc_synchronization_engine.entity.RetryQueue;
import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;
import com.example.cdc_synchronization_engine.repository.RetryQueueRepository;
import com.example.cdc_synchronization_engine.service.RetryQueueService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RetryQueueServiceImpl
        implements RetryQueueService {

    private final RetryQueueRepository repository;

    private final ObjectMapper objectMapper;

    @Value("${cdc.kafka.topic.orders}")
    private String ordersTopic;

    @Value("${cdc.kafka.topic.customers}")
    private String customersTopic;

    @Value("${cdc.kafka.topic.products}")
    private String productsTopic;

    @Value("${cdc.kafka.topic.inventory}")
    private String inventoryTopic;

    @Value("${cdc.kafka.topic.payments}")
    private String paymentsTopic;

    @Override
    public void saveForRetry(
            CDCEvent event,
            String error) {

        try {

            RetryQueue retry = new RetryQueue();

            retry.setTopicName(resolveTopic(event.getEntityName()));

            retry.setPayload(
                    objectMapper.writeValueAsString(event));

            retry.setRetryAttempt(0);

            retry.setStatus("PENDING");

            retry.setNextRetryTime(
                    LocalDateTime.now().plusMinutes(1));

            repository.save(retry);

        } catch (Exception ex) {

            throw new RuntimeException(
                    "Unable to save retry event",
                    ex
            );
        }
    }

    private String resolveTopic(String entityName) {

        if (entityName == null) {
            return ordersTopic;
        }

        return switch (entityName.toUpperCase()) {

            case "ORDER" -> ordersTopic;

            case "CUSTOMER" -> customersTopic;

            case "PRODUCT" -> productsTopic;

            case "INVENTORY" -> inventoryTopic;

            case "PAYMENT" -> paymentsTopic;

            default -> ordersTopic;
        };
    }
}