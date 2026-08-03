package com.example.cdc_synchronization_engine.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.entity.RetryQueue;
import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;
import com.example.cdc_synchronization_engine.kafka.producer.CDCEventProducer;
import com.example.cdc_synchronization_engine.repository.RetryQueueRepository;
import com.example.cdc_synchronization_engine.service.DeadLetterService;
import com.example.cdc_synchronization_engine.service.RetryPublisherService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RetryPublisherServiceImpl
        implements RetryPublisherService {

    private final RetryQueueRepository retryQueueRepository;

    private final CDCEventProducer producer;

    private final DeadLetterService deadLetterService;

    private final ObjectMapper objectMapper;

    private static final int MAX_RETRY = 3;

    @Override
    public void processRetryQueue() {

        List<RetryQueue> retryEvents =
                retryQueueRepository
                        .findByStatusAndNextRetryTimeLessThanEqual(
                                "PENDING",
                                LocalDateTime.now());

        if (retryEvents.isEmpty()) {
            return;
        }

        for (RetryQueue retry : retryEvents) {

            try {

                producer.publishRaw(
                        retry.getTopicName(),
                        retry.getPayload());

                retry.setStatus("SUCCESS");

                retryQueueRepository.save(retry);

                log.info("Retry successful : {}", retry.getId());

            } catch (Exception ex) {

                int attempts =
                        retry.getRetryAttempt() == null
                                ? 1
                                : retry.getRetryAttempt() + 1;

                retry.setRetryAttempt(attempts);

                if (attempts >= MAX_RETRY) {

                	CDCEvent event;

                	try {

                	    event = objectMapper.readValue(
                	            retry.getPayload(),
                	            CDCEvent.class);

                	} catch (Exception ex2) {

                	    throw new RuntimeException(
                	            "Unable to deserialize CDC Event",
                	            ex);

                	}
                    deadLetterService.saveDeadLetter(
                            event,
                            ex.getMessage());

                    retry.setStatus("FAILED");

                    log.error("Moved to Dead Letter Queue : {}",
                            retry.getId());

                } else {

                    retry.setNextRetryTime(
                            LocalDateTime.now().plusMinutes(1));

                }

                retryQueueRepository.save(retry);
            }

        }

    }

}