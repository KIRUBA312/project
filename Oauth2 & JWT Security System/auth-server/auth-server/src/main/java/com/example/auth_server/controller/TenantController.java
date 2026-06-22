package com.example.auth_server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_server.entity.Tenant;
import com.example.auth_server.service.TenantService;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

	@Autowired
	private TenantService tenantService;
	
	@PostMapping
	public ResponseEntity<Tenant> createTenant(
			@RequestBody Tenant tenant){
		return ResponseEntity.ok(tenantService.createTenant(
				tenant));
	}
	@GetMapping
	public ResponseEntity<List<Tenant>> getAllTenants(){
		return ResponseEntity.ok(tenantService.getAllTenants());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Tenant> getTenantById(@PathVariable Long id){
		return ResponseEntity.ok(tenantService.getTenantById(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<Tenant> updateTenant(@PathVariable Long id,
			@RequestBody Tenant tenant){
		return ResponseEntity.ok(tenantService.updateTenant(id,tenant));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTenant(@PathVariable Long id){
		return ResponseEntity.ok(tenantService.deleteTenant(id));
	}
}
