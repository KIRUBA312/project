package com.example.enterprise_iam.util;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class OtpUtil {

	private static final SecureRandom random = new SecureRandom();
	
	public String generateOtp() {
		int otp = 100000 + random.nextInt(900000);
		
		return String.valueOf(otp);
	}
	public boolean verifyOtp(String generateOtp,String enteredOtp) {
		if (generateOtp == null || enteredOtp == null) {
			return false;
		}
		return generateOtp.equals(enteredOtp);
	}
	
}
