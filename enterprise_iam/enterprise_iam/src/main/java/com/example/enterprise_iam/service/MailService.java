package com.example.enterprise_iam.service;

public interface MailService {

	void sendVerificationEmail(String email, String token);
	void sendPasswordResetEmail(String email, String token);
}
