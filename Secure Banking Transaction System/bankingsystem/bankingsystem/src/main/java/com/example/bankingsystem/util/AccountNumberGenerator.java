package com.example.bankingsystem.util;

import java.util.Random;

public class AccountNumberGenerator {
	
	private AccountNumberGenerator() {}

	public static String generateAccountNumber() {
		// TODO Auto-generated method stub
		Random random = new Random();
		long number = 1000000000L + 
				(long)(random.nextDouble() * 9000000000L);
		
		return "ACC" + number;
	}

}
