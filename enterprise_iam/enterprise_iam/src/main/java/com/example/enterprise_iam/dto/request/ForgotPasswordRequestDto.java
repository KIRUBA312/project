package com.example.enterprise_iam.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordRequestDto {

	@Email
	@NotBlank
	private String email;
	
	public ForgotPasswordRequestDto() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
