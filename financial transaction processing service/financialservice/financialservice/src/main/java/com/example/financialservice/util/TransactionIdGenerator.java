package com.example.financialservice.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class TransactionIdGenerator {

	public String generateTransactionId() {
		
		return UUID.randomUUID().toString();
	}
}
