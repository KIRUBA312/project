package com.example.enterprise_iam.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class VerifyOtpRequestDto {

	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String otp;

	public VerifyOtpRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
	
}
