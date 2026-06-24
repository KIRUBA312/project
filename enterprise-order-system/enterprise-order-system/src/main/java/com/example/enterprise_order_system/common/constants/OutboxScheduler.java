package com.example.enterprise_order_system.common.constants;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.enterprise_order_system.entity.OutboxEvent;
import com.example.enterprise_order_system.repository.OutBoxRepository;
import com.example.enterprise_order_system.service.KafkaProducerService;

@Component
public class OutboxScheduler {

	@Autowired
	private OutBoxRepository outBoxRepository;
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	@Scheduled(fixedRate = 30000)
	public void publishPendingEvents() {
		List<OutboxEvent> events = outBoxRepository
				.findByStatus("PENDING");
		for(OutboxEvent event : events) {
			kafkaProducerService.publishMessage(
					event.getEventType(),event.getPayload());
			event.setStatus("PUBLISHED");
			outBoxRepository.save(event);
		}
	}
	
}
