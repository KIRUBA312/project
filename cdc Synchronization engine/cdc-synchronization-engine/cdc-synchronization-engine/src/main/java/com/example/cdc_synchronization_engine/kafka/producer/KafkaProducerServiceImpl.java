package com.example.cdc_synchronization_engine.kafka.producer;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;
import com.example.cdc_synchronization_engine.util.TopicResolver;

@Service
public class KafkaProducerServiceImpl
        implements KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final TopicResolver topicResolver;

    public KafkaProducerServiceImpl(
            KafkaTemplate<String, Object> kafkaTemplate,
            TopicResolver topicResolver) {

        this.kafkaTemplate = kafkaTemplate;
        this.topicResolver = topicResolver;
    }

    @Override
    public void publish(String entityName,
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
                topicResolver.resolveTopic(entityName),
                entityId.toString(),
                event);

    }

}