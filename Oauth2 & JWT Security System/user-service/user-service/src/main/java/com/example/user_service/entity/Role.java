package com.example.user_service.entity;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "role_name")
	private String roleName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_permissions",
				joinColumns = @JoinColumn(name = "role_id"),
				inverseJoinColumns = @JoinColumn(name = "permission_id")
	)
	private List<Permission> permissions = new ArrayList<>();

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

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	
	
}
