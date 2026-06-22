package com.example.auth_server.exception;

public class TenantNotFoundException extends RuntimeException{

	public TenantNotFoundException(String message) {
		super(message);
	}
}
