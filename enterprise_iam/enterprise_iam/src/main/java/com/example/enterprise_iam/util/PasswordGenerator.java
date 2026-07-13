package com.example.enterprise_iam.util;

import java.security.SecureRandom;
import java.util.Iterator;

import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {

	private static final String CHARACTERS =
			"ABCDEFGHIJKLMNOPPQRSTUVWXYZ"
			+"abcdefghijklmnopqrstuvwxyz"+"0123456789"+"@#$%&*!";
	
	private static final SecureRandom RANDOM = new SecureRandom();
	
	public String generatePassword(int length) {
		
		StringBuilder password = new StringBuilder();
		for(int i=0;i<length;i++) {
			int index = RANDOM.nextInt(CHARACTERS.length());
			password.append(CHARACTERS.charAt(index));
		}
		return password.toString();
	}
	
}
