package com.example.cdc_synchronization_engine.kafka.producer;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CDCEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

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

    public void publishEvent(
            String topic,
            String entityName,
            Long entityId,
            String operation,
            Object payload) {

        CDCEvent event = new CDCEvent();

        event.setEventId(UUID.randomUUID());
        event.setEntityName(entityName);
        event.setEntityId(entityId);
        event.setOperation(operation);
        event.setPayload(payload);
        event.setCorrelationId(UUID.randomUUID().toString());
        event.setEventTime(LocalDateTime.now());

        kafkaTemplate.send(
                topic,
                String.valueOf(entityId),
                event
        );
        System.out.println("=================================");
        System.out.println("PUBLISHING TO KAFKA");
        System.out.println("Topic : " + topic);
        System.out.println("Entity : " + entityName);
        System.out.println("Operation : " + operation);
        System.out.println("Payload : " + payload);
        System.out.println("=================================");
    }

    public void publish(CDCEvent event) {

        kafkaTemplate.send(
                resolveTopic(event.getEntityName()),
                String.valueOf(event.getEntityId()),
                event
        );
    }

    public void publish(
            String topic,
            CDCEvent event) {

        kafkaTemplate.send(
                topic,
                String.valueOf(event.getEntityId()),
                event
        );
    }
    
    public void publishRaw(
            String topic,
            String payload) {

        kafkaTemplate.send(
                topic,
                payload
        );

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