package com.example.enterprise_iam.exception;

public class InvalidTokenException extends RuntimeException{

	public InvalidTokenException(String message) {
		super(message);
	}
}
