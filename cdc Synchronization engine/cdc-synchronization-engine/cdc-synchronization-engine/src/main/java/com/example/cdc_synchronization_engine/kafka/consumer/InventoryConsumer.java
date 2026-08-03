package com.example.cdc_synchronization_engine.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;
import com.example.cdc_synchronization_engine.service.CDCProcessingService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InventoryConsumer {

    private final CDCProcessingService processingService;

    @KafkaListener(
            topics = "${cdc.kafka.topic.inventory}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(CDCEvent event,
                        Acknowledgment acknowledgment) {

        processingService.processEvent(event);

        acknowledgment.acknowledge();
    }
}