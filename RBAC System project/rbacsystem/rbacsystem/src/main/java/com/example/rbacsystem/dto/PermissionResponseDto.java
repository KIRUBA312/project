package com.example.rbacsystem.dto;

public class PermissionResponseDto {

	private Long id;
	private String permissionName;
	
	public PermissionResponseDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
	
	
}
