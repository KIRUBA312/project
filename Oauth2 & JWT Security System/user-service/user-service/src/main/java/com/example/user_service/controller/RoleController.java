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

import com.example.user_service.dto.RoleRequestDto;
import com.example.user_service.dto.RoleResponseDto;
import com.example.user_service.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<RoleResponseDto> createRole(
			@RequestBody RoleRequestDto dto){
		return ResponseEntity.ok(roleService.createRole(dto));
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<RoleResponseDto> getRoleById(
			@PathVariable Long id){
		return ResponseEntity.ok(roleService.getRoleById(id));
	}
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<List<RoleResponseDto>> getAllRoles(){
		return ResponseEntity.ok(roleService.getAllRoles());
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<RoleResponseDto> updateRole(
			@PathVariable Long id,@RequestBody RoleRequestDto dto){
		return ResponseEntity.ok(roleService.updateRole(id, dto));
	}
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteRole(
			@PathVariable Long id){
		return ResponseEntity.ok(roleService.deleteRole(id));
	}
	
	
}
