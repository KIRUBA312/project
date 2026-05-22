package com.example.financialservice.util;

import java.util.Iterator;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class AccountNumberGenerator {

	public String generateAccountNumber() {
		
		Random random = new Random();
		
		StringBuilder accountNumber = new StringBuilder();
		
		for(int i = 0;i<12;i++) {
			int digit = random.nextInt(10);
			accountNumber.append(digit);
		}
		return accountNumber.toString();
	}
}
