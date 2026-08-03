package com.example.cdc_synchronization_engine.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.entity.CDCEventLog;
import com.example.cdc_synchronization_engine.entity.ElasticsearchSyncQueue;
import com.example.cdc_synchronization_engine.entity.IdempotencyRecord;
import com.example.cdc_synchronization_engine.entity.KafkaProcessingStatus;
import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;
import com.example.cdc_synchronization_engine.repository.CDCEventLogRepository;
import com.example.cdc_synchronization_engine.repository.ElasticsearchSyncQueueRepository;
import com.example.cdc_synchronization_engine.repository.IdempotencyRecordRepository;
import com.example.cdc_synchronization_engine.repository.KafkaProcessingStatusRepository;
import com.example.cdc_synchronization_engine.service.AuditLogService;
import com.example.cdc_synchronization_engine.service.CDCProcessingService;
import com.example.cdc_synchronization_engine.service.ProcessingMetricService;
import com.example.cdc_synchronization_engine.service.SynchronizationStatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CDCProcessingServiceImpl implements CDCProcessingService {

    private final CDCEventLogRepository cdcEventLogRepository;
    private final IdempotencyRecordRepository idempotencyRecordRepository;
    private final KafkaProcessingStatusRepository kafkaProcessingStatusRepository;
    private final SynchronizationStatusService synchronizationStatusService;
    private final ElasticsearchSyncQueueRepository elasticsearchSyncQueueRepository;
    private final ObjectMapper objectMapper;
    private final ProcessingMetricService processingMetricService;
    private final AuditLogService auditLogService;
    @Override
    public void processEvent(CDCEvent event) {
    	long start = System.currentTimeMillis();
        try {
        	
			String eventId = event.getEventId().toString();
			if(idempotencyRecordRepository.existsByEventId(eventId)) {
				return;
			}
			CDCEventLog log = new CDCEventLog();
			log.setTableName(event.getEntityName());
			log.setOperation(event.getOperation());
        log.setEntityId(event.getEntityId());
        log.setCorrelationId(event.getCorrelationId());

        log.setPayload(
                objectMapper.writeValueAsString(event.getPayload())
        );

        System.out.println("1");
        cdcEventLogRepository.save(log);

        IdempotencyRecord record = new IdempotencyRecord();

        record.setEventId(eventId);
        record.setTopicName(event.getEntityName());
        record.setProcessed(true);
        record.setProcessedAt(LocalDateTime.now());

        System.out.println("2");
        idempotencyRecordRepository.save(record);

        ElasticsearchSyncQueue queue =
                new ElasticsearchSyncQueue();

        queue.setIndexName(
                event.getEntityName().toLowerCase());

        queue.setPayload(convertToJson(event));

        queue.setSyncStatus("PENDING");

        System.out.println("3");

        elasticsearchSyncQueueRepository.save(queue);

        KafkaProcessingStatus status =
                new KafkaProcessingStatus();

        status.setTopicName(
                event.getEntityName().toLowerCase() + "-events");

        status.setMessageKey(
                String.valueOf(event.getEntityId()));

        status.setProcessingStatus("PROCESSED");

        status.setRetryCount(0);

        status.setPartitionNumber(0);

        status.setOffsetNumber(0L);

        status.setProcessedAt(LocalDateTime.now());

        System.out.println("4");

        kafkaProcessingStatusRepository.save(status);
        System.out.println("5");

        processingMetricService.recordSuccess(
                event.getEntityName(),
                System.currentTimeMillis() - start
        );
        System.out.println("6");

        synchronizationStatusService.markSuccess(event);
        System.out.println("7");

        auditLogService.saveAudit(event);
        System.out.println("done");

		} catch (Exception ex) {
			// TODO: handle exception
			synchronizationStatusService.markFailure(event);
			processingMetricService.recordFailure(
			        event.getEntityName(),
			        System.currentTimeMillis() 
			        - start);
			auditLogService.saveAudit(event);
			throw new RuntimeException(ex);
		}
    }
    
    private String convertToJson(Object object) {

        try {

            return objectMapper.writeValueAsString(object);

        } catch (Exception ex) {

            throw new RuntimeException(ex);

        }

    }
}