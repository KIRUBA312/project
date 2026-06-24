package com.example.enterprise_order_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void publishMessage(String topic,String message) {
		kafkaTemplate.send(topic,message);
	}
	
}
