package com.example.auth_server.service;

import java.util.List;

import com.example.auth_server.entity.Tenant;

public interface TenantService {

	Tenant createTenant(Tenant tenant);

	List<Tenant> getAllTenants();

	Tenant getTenantById(Long id);

	Tenant updateTenant(Long id, Tenant tenant);

	String deleteTenant(Long id);

}
