package com.example.cdc_synchronization_engine.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;
import com.example.cdc_synchronization_engine.service.CDCProcessingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CDCEventConsumer {

    private final CDCProcessingService cdcProcessingService;

    @KafkaListener(
            topics = {
                    "${cdc.kafka.topic.orders}",
                    "${cdc.kafka.topic.customers}",
                    "${cdc.kafka.topic.products}",
                    "${cdc.kafka.topic.inventory}",
                    "${cdc.kafka.topic.payments}"
            },
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(
            CDCEvent event,
            Acknowledgment acknowledgment) {

        try {

            log.info(
                    "Received Event : {} {}",
                    event.getEntityName(),
                    event.getOperation());

            cdcProcessingService.processEvent(event);

            acknowledgment.acknowledge();

        } catch (Exception ex) {

            log.error(
                    "Kafka Consumer Error",
                    ex);

        }
    }
}