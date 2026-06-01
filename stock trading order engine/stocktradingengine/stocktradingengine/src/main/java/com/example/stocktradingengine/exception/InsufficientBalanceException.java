package com.example.stocktradingengine.exception;

public class InsufficientBalanceException extends RuntimeException{

	public InsufficientBalanceException(String message) {
		super(message);
	}
	
}
