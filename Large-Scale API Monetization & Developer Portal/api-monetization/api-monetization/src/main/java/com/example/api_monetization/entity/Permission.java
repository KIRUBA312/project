package com.example.api_monetization.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "permission_name",nullable = false,unique = true,length = 150)
	private String permissionName;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "module_name", length = 100)
	private String moduleName;
	
	@OneToMany(mappedBy = "permission",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			orphanRemoval = true)
	private List<RolePermission> rolePermissions = new ArrayList<>();
	
	public Permission() {}

	public Permission(Long id, String permissionName, 
			String description, String moduleName,
			List<RolePermission> rolePermissions) {
		this.id = id;
		this.permissionName = permissionName;
		this.description = description;
		this.moduleName = moduleName;
		this.rolePermissions = rolePermissions;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public List<RolePermission> getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}
	
	

}
