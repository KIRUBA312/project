package com.example.rbacsystem.dto;

import java.util.List;

public class RoleRequestDto {

	private String roleName;
	private List<Long> permissionIds;
	
	public RoleRequestDto() {}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Long> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(List<Long> permissionIds) {
		this.permissionIds = permissionIds;
	}
	
	
	
}
