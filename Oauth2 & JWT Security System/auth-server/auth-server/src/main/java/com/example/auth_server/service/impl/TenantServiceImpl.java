package com.example.auth_server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth_server.entity.Tenant;
import com.example.auth_server.exception.TenantNotFoundException;
import com.example.auth_server.repository.TenantRepository;
import com.example.auth_server.service.TenantService;

@Service
public class TenantServiceImpl implements TenantService{
	
	@Autowired
	private TenantRepository tenantRepository;

	@Override
	public Tenant createTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return tenantRepository.save(tenant);
	}

	@Override
	public List<Tenant> getAllTenants() {
		// TODO Auto-generated method stub
		return tenantRepository.findAll();
	}

	@Override
	public Tenant getTenantById(Long id) {
		// TODO Auto-generated method stub
		return tenantRepository.findById(id)
				.orElseThrow(() -> new TenantNotFoundException("Tenant not found"));
	}

	@Override
	public Tenant updateTenant(Long id, Tenant tenant) {
		// TODO Auto-generated method stub
		Tenant existing = getTenantById(id);
		existing.setTenantName(tenant.getTenantName());
		existing.setTenantName(tenant.getTenantName());
		existing.setTenantCode(tenant.getTenantCode());
		existing.setActive(tenant.getActive());
		return tenantRepository.save(existing);
	}

	@Override
	public String deleteTenant(Long id) {
		// TODO Auto-generated method stub
		Tenant tenant = getTenantById(id);
		tenantRepository.delete(tenant);
		return "Tenant deleted successfully";
	}

}
