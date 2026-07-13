package com.example.enterprise_iam.dto.request;

import jakarta.validation.constraints.NotBlank;

public class PermissionRequestDto {

	@NotBlank
	private String name;
	private String description;
	
	public PermissionRequestDto() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
