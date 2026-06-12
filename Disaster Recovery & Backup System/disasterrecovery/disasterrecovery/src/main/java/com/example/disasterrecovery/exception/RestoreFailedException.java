package com.example.disasterrecovery.exception;

public class RestoreFailedException extends RuntimeException{

	public RestoreFailedException(String message) {
		super(message);
	}
}
