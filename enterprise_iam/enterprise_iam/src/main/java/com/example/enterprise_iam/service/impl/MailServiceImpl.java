package com.example.enterprise_iam.service.impl;

import org.springframework.stereotype.Service;

import com.example.enterprise_iam.service.MailService;

@Service
public class MailServiceImpl implements MailService{

	@Override
	public void sendVerificationEmail(String email, String token) {
		// TODO Auto-generated method stub
		System.out.println("=========================================");
        System.out.println("EMAIL VERIFICATION");
        System.out.println("To : " + email);
        System.out.println("Verification Link :");
        System.out.println(
                "http://localhost:8080/api/auth/verify?token=" + token);
        System.out.println("=========================================");
	}

	@Override
	public void sendPasswordResetEmail(String email, String token) {
		// TODO Auto-generated method stub
		System.out.println("=========================================");
        System.out.println("PASSWORD RESET");
        System.out.println("To : " + email);
        System.out.println("Reset Link :");
        System.out.println(
                "http://localhost:8080/api/auth/reset-password?token=" + token);
        System.out.println("=========================================");
	}

}
