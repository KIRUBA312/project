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

import com.example.auth_server.entity.Permission;
import com.example.auth_server.service.PermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@PostMapping
	public ResponseEntity<Permission> createPermission(
			@RequestBody Permission permission){
		return ResponseEntity.ok(permissionService
				.createPermission(permission));
	}
	@GetMapping
	public ResponseEntity<List<Permission>> getAllPermission(){
		return ResponseEntity.ok(permissionService.getAllPermissions());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Permission> getPermissionById(
			@PathVariable Long id){
		return ResponseEntity.ok(permissionService
				.getPermissionById(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<Permission> updatePermission(
			@PathVariable Long id,@RequestBody Permission permission){
		return ResponseEntity.ok(permissionService.updatePermission(
				id,permission));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePermission(
			@PathVariable Long id){
		return ResponseEntity.ok(permissionService.deletePermission(id));
	}
	
}
