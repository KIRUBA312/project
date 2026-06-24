package com.example.enterprise_order_system.exception;

public class PaymentFailedException extends RuntimeException{

	public PaymentFailedException(String message) {
		super(message);
	}
	
}
