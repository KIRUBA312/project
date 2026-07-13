package com.example.enterprise_iam.exception;

public class BadRequestException extends RuntimeException{

	public BadRequestException(String message) {
		super(message);
	}
}
