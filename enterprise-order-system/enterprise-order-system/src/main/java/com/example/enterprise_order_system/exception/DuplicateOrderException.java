package com.example.enterprise_order_system.exception;

public class DuplicateOrderException extends RuntimeException{

	public DuplicateOrderException(String message) {
		super(message);
	}
}
