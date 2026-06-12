package com.example.disasterrecovery.exception;

public class BackupFailedException extends RuntimeException{

	public BackupFailedException(String message) {
		super(message);
	}
}
