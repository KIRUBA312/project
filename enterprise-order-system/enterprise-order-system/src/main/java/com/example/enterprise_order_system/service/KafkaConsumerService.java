package com.example.enterprise_order_system.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

	@KafkaListener(
			topics = "order-created",
			groupId = "enterprise-group")
	public void consumerOrderCreated(String message) {
		System.out.println("Order Created Event Received : "+message);
	}
	@KafkaListener(topics = "payment-success",
			groupId = "enterprise-group")
	public void consumePaymentSuccess(String message) {
		System.out.println(
				"Payment Success Event Received : "+message);
	}
	@KafkaListener(topics = "payment-failed",
			groupId = "enterprise-group")
	public void consumePaymentFailed(String message) {
		System.out.println("Payment Failed Event Received : "+message);
	}
	
}
