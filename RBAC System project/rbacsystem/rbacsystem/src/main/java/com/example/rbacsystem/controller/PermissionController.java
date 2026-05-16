package com.example.rbacsystem.controller;

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

import com.example.rbacsystem.dto.PermissionRequestDto;
import com.example.rbacsystem.dto.PermissionResponseDto;
import com.example.rbacsystem.service.PermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@PostMapping
	public ResponseEntity<PermissionResponseDto> createPermission(
			@RequestBody PermissionRequestDto dto){
		
		return ResponseEntity.ok(permissionService.createPermission(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<PermissionResponseDto>> getAllPermissions(){
		return ResponseEntity.ok(permissionService.getAllPermissions());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PermissionResponseDto> getPermissionById(
			@PathVariable Long id){
		
		return ResponseEntity.ok(permissionService.getPermissionById(id));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PermissionResponseDto> updatePermission(
			@PathVariable Long id,@RequestBody PermissionRequestDto dto){
		
		return ResponseEntity.ok(permissionService
				.updatePermission(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePermission(
			@PathVariable Long id){
		
		permissionService.deletePermission(id);
		
		return ResponseEntity.ok("Permission deleted successfully");
	}
	
}
