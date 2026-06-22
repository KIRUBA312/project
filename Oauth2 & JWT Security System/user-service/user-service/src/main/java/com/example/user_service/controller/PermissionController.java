package com.example.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.dto.PermissionRequestDto;
import com.example.user_service.dto.PermissionResponseDto;
import com.example.user_service.service.PermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PermissionResponseDto> createPermission(
			@RequestBody PermissionRequestDto dto){
		return ResponseEntity.ok(permissionService
				.createPermission(dto));
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<PermissionResponseDto> getPermissionById(
			@PathVariable Long id){
		return ResponseEntity.ok(permissionService
				.getPermissionById(id));
	}
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<List<PermissionResponseDto>>
	getAllPermissions(){
		return ResponseEntity.ok(permissionService
				.getAllPermissions());
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PermissionResponseDto> updatePermission(
			@PathVariable Long id,@RequestBody PermissionRequestDto dto){
		return ResponseEntity.ok(permissionService
				.updatePermission(id,dto));
	}
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deletePermission(
			@PathVariable Long id){
		return ResponseEntity.ok(permissionService
				.deletePermission(id));
	}
	
	
}
