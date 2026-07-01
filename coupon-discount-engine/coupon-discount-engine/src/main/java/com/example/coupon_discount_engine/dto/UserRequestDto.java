package com.example.coupon_discount_engine.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequestDto {
	@NotBlank(message = "Name is required")
	private String name;
	
	@Email(message = "Invalid Email")
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "Mobile is required")
	private String mobile;
	
	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
