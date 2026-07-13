package com.example.enterprise_iam.exception;

public class AccountLockedException extends RuntimeException{

	public AccountLockedException(String message) {
		super(message);
	}
}
