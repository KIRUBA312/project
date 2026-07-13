package com.example.enterprise_iam.entity;

import jakarta.persistence.Entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name",length = 100)
	private String name;
	
	@Column(name = "description",length = 255)
	private String description;
	
	@OneToMany(mappedBy = "permission",cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<RolePermission> rolePermissions;
	
	public Permission() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<RolePermission> getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}
	
	

}
