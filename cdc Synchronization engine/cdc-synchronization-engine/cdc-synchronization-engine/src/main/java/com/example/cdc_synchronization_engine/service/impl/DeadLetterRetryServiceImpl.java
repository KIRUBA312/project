package com.example.cdc_synchronization_engine.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.entity.DeadLetterEvent;
import com.example.cdc_synchronization_engine.exception.ErrorCode;
import com.example.cdc_synchronization_engine.exception.ResourceNotFoundException;
import com.example.cdc_synchronization_engine.kafka.producer.CDCEventProducer;
import com.example.cdc_synchronization_engine.repository.DeadLetterEventRepository;
import com.example.cdc_synchronization_engine.service.DeadLetterRetryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DeadLetterRetryServiceImpl
        implements DeadLetterRetryService {

    private final DeadLetterEventRepository repository;

    private final CDCEventProducer producer;

    @Override
    public void retryDeadLetterEvent(Long id) {

        DeadLetterEvent event =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ErrorCode.RESOURCE_NOT_FOUND,
                                        "Dead Letter Event not found"));

        producer.publishRaw(
                event.getTopicName(),
                event.getPayload());

        event.setRetryCount(
                event.getRetryCount() + 1);

        repository.save(event);

    }

}