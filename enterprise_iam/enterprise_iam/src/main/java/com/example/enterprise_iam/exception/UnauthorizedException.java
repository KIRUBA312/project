package com.example.enterprise_iam.exception;

public class UnauthorizedException extends RuntimeException{

	public UnauthorizedException(String message) {
		super(message);
	}
}
