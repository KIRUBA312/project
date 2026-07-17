package com.example.api_monetization.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "role_name",nullable = false,unique = true,length = 100)
	private String roleName;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@OneToMany(mappedBy = "role",cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,orphanRemoval = true)
	private List<UserRole> userRoles = new ArrayList<>();
	
	@OneToMany(mappedBy = "role",cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,orphanRemoval = true)
	private List<RolePermission> rolePermissions = new ArrayList<>();
	
	public Role() {}

	public Role(Long id, String roleName, String description) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.description = description;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public List<RolePermission> getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}
	
	

}
