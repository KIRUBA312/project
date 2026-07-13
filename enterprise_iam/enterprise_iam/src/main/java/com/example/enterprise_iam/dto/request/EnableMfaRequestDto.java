package com.example.enterprise_iam.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EnableMfaRequestDto {

	@Email
	@NotBlank
	private String email;

	public EnableMfaRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
