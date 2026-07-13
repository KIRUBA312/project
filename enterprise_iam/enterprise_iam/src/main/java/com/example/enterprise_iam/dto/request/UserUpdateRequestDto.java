package com.example.enterprise_iam.dto.request;

import jakarta.validation.constraints.Pattern;

public class UserUpdateRequestDto {
	
	private String firstName;
	private String lastName;
	
	@Pattern(regexp = "^[0-9]{10}$")
	private String phone;
	
	public UserUpdateRequestDto() {}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
