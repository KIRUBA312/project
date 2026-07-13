package com.example.enterprise_iam.exception;

public class TokenExpiredException extends RuntimeException{

	public TokenExpiredException(String message) {
		super(message);
	}
}
