package com.example.user_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "permission_name")
	private String permissionName;

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
