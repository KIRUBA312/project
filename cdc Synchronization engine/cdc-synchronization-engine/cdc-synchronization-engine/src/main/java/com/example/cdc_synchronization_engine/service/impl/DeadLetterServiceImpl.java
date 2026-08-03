package com.example.cdc_synchronization_engine.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cdc_synchronization_engine.dto.DeadLetterEventResponse;
import com.example.cdc_synchronization_engine.entity.DeadLetterEvent;
import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;
import com.example.cdc_synchronization_engine.mapper.DeadLetterEventMapper;
import com.example.cdc_synchronization_engine.repository.DeadLetterEventRepository;
import com.example.cdc_synchronization_engine.service.DeadLetterService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DeadLetterServiceImpl implements DeadLetterService{

	private final DeadLetterEventRepository repository;
	private final DeadLetterEventMapper mapper;
	private final ObjectMapper objectMapper;
	
	@Override
	public void saveDeadLetter(CDCEvent event, String error) {
		// TODO Auto-generated method stub
        try {

            DeadLetterEvent dead = new DeadLetterEvent();
            dead.setTopicName( event.getEntityName());
            dead.setEventKey( String.valueOf(
                            event.getEntityId()));
            dead.setPayload( objectMapper.writeValueAsString(
                            event));
            dead.setRetryCount(3);
            dead.setErrorMessage(error);
            repository.save(dead);

        }

        catch (Exception ex) {
            throw new RuntimeException(ex);

        }
		
	}
	@Override
	public List<DeadLetterEventResponse> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll().stream().map(mapper::toResponse)
				.toList();
	}
	
	
}
