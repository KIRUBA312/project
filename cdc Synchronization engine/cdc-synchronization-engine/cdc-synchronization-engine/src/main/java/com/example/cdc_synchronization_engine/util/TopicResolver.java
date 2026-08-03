package com.example.cdc_synchronization_engine.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TopicResolver {

    @Value("${cdc.kafka.topic.customers}")
    private String customerTopic;

    @Value("${cdc.kafka.topic.products}")
    private String productTopic;

    @Value("${cdc.kafka.topic.inventory}")
    private String inventoryTopic;

    @Value("${cdc.kafka.topic.orders}")
    private String orderTopic;

    @Value("${cdc.kafka.topic.payments}")
    private String paymentTopic;

    public String resolveTopic(String entity) {

        return switch (entity.toUpperCase()) {

            case "CUSTOMER" -> customerTopic;

            case "PRODUCT" -> productTopic;

            case "INVENTORY" -> inventoryTopic;

            case "ORDER" -> orderTopic;

            case "PAYMENT" -> paymentTopic;

            default -> throw new IllegalArgumentException(
                    "Unknown entity : " + entity);
        };
    }

}