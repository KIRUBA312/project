package com.example.rbacsystem.dto;

import java.util.List;

public class RoleResponseDto {

	private Long id;
	private String roleName;
	private List<String> permissionName;
	
	public RoleResponseDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<String> getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(List<String> permissionName) {
		this.permissionName = permissionName;
	}

	

	
	
	
	
}
