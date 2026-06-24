package com.example.enterprise_order_system.common.constants;

public final class KafkaTopics {

	private KafkaTopics() {
	}
	
	public static final String ORDER_CREATED = "order-created";
	public static final String PAYMENT_SUCCESS = "payment-success";
	public static final String PAYMENT_FAILED = "payment-failed";
	
}
