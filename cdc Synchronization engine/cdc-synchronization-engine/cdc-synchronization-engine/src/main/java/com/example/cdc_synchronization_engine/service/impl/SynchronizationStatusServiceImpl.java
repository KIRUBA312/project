package com.example.cdc_synchronization_engine.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.entity.SynchronizationStatus;
import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;
import com.example.cdc_synchronization_engine.repository.SynchronizationStatusRepository;
import com.example.cdc_synchronization_engine.service.SynchronizationStatusService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SynchronizationStatusServiceImpl
        implements SynchronizationStatusService {

    private final SynchronizationStatusRepository repository;

	@Override
	public void markSuccess(CDCEvent event) {
		// TODO Auto-generated method stub
		SynchronizationStatus status =
                repository.findByEntityNameAndEntityId(
                        event.getEntityName(),
                        event.getEntityId())
                .orElse(new SynchronizationStatus());

        status.setEntityName(event.getEntityName());
        status.setEntityId(event.getEntityId());
        status.setStatus("SUCCESS");
        status.setLastSync(LocalDateTime.now());
        status.setKafkaOffset(0L);
        status.setElasticsearchDocumentId(null);
        repository.save(status);
	}

	@Override
	public void markFailure(CDCEvent event) {
		// TODO Auto-generated method stub
		SynchronizationStatus status =
                repository.findByEntityNameAndEntityId(
                        event.getEntityName(),
                        event.getEntityId())
                .orElse(new SynchronizationStatus());

        status.setEntityName(event.getEntityName());
        status.setEntityId(event.getEntityId());
        status.setStatus("FAILED");
        status.setLastSync(LocalDateTime.now());
        status.setKafkaOffset(0L);
        status.setElasticsearchDocumentId(null);
        repository.save(status);
	}

    
}