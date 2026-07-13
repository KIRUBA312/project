package com.example.enterprise_iam.exception;

public class EmailNotVerifiedException extends RuntimeException{

	public EmailNotVerifiedException(String message) {
		super(message);
	}
}
