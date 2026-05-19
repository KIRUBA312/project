package com.example.bankingsystem.exception;

public class InvalidTransferException extends RuntimeException{

	public InvalidTransferException(String message) {
		super(message);
	}
}
