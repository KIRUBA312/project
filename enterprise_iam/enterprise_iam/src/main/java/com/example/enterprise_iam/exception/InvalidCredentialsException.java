package com.example.enterprise_iam.exception;

public class InvalidCredentialsException extends RuntimeException{

	public InvalidCredentialsException(String message) {
		super(message);
	}
}
