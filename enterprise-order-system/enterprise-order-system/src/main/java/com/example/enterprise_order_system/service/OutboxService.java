package com.example.enterprise_order_system.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprise_order_system.entity.OutboxEvent;
import com.example.enterprise_order_system.repository.OutBoxRepository;

@Service
public class OutboxService {

	@Autowired
	private OutBoxRepository outBoxRepository;
	
	public void saveEvent(String aggregateType,Long aggregateId,
			String eventType,String payload) {
		
		OutboxEvent event = new OutboxEvent();
		event.setAggregateType(aggregateType);
		event.setAggregateId(aggregateId);
		event.setEventType(eventType);
		event.setPayload(payload);
		event.setStatus("PENDING");
		event.setCreatedAt(LocalDateTime.now());
		outBoxRepository.save(event);
	}
	
}
