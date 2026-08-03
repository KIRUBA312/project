package com.example.cdc_synchronization_engine.kafka.producer;

public interface KafkaProducerService {

    void publish(String entityName,
                 Long entityId,
                 String operation,
                 Object payload);

}