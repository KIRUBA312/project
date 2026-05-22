package com.example.financialservice.exception;

public class DuplicateRequestException extends RuntimeException{

	public DuplicateRequestException(String message) {
		super(message);
	}
}
