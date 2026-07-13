package com.example.enterprise_iam.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ResetPasswordRequestDto {

	@NotBlank
	private String token;
	
	@NotBlank
	@Size(min = 8)
	private String newPassword;
	
	public ResetPasswordRequestDto() {}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	
}
