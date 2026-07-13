package com.example.enterprise_iam.exception;

public class PasswordMismatchException extends RuntimeException{

	public PasswordMismatchException(String message) {
		super(message);
	}
}
