package com.example.taskmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequestDto {

	@NotBlank(message = "Name is required")
	private String name;
	
	@Email(message = "Invalid email")
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "Role is required")
	private String role;
	
	public UserRequestDto() {}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
