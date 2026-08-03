package com.example.cdc_synchronization_engine.kafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.stereotype.Component;

@Component
public class KafkaErrorHandler extends DefaultErrorHandler {

    private static final Logger log =
            LoggerFactory.getLogger(KafkaErrorHandler.class);

    @Override
    public void handleRemaining(
            Exception thrownException,
            java.util.List<
            org.apache.kafka.clients.consumer.ConsumerRecord<?, ?>> records,
            org.apache.kafka.clients.consumer.Consumer<?, ?> consumer,
            org.springframework.kafka.listener.MessageListenerContainer container) {

        log.error("Kafka Consumer Error : {}",
                thrownException.getMessage());

        super.handleRemaining(
                thrownException,
                records,
                consumer,
                container);

    }

}