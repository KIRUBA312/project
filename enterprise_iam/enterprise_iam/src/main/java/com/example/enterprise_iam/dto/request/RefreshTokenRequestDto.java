package com.example.enterprise_iam.dto.request;

import jakarta.validation.constraints.NotBlank;

public class RefreshTokenRequestDto {

	@NotBlank
	private String refreshToken;
	
	public RefreshTokenRequestDto() {}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
}
