package com.example.enterprise_iam.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role_permissions")
public class RolePermission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_id", nullable = false)
	private Permission permission;
	
	public RolePermission() {}

	public RolePermission(Long id, Role role, Permission permission) {
		super();
		this.id = id;
		this.role = role;
		this.permission = permission;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	

}
