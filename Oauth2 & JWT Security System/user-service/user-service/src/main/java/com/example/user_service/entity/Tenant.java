package com.example.user_service.entity;

import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@Table(name = "tenants")
public class Tenant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tenant_name")
	private String tenantName;
	
	@Column(name = "tenant_code")
	private String tenantCode;
	
	@Column(name = "active")
	private Boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	

}
