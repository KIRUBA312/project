package com.example.enterprise_iam.controller;

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

import com.example.enterprise_iam.dto.request.RoleRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.RoleResponseDto;
import com.example.enterprise_iam.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@PostMapping
	public ResponseEntity<RoleResponseDto> createRole(@Valid 
			@RequestBody RoleRequestDto request){
		
		return ResponseEntity.ok(roleService.createRole(request));
	}
	@PutMapping("/{id}")
	public ResponseEntity<RoleResponseDto> updateRole(
			@PathVariable Long id,@Valid @RequestBody RoleRequestDto request){
		return ResponseEntity.ok(roleService.updateRole(id,request));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseDto> deleteRole(
			@PathVariable Long id){
		return ResponseEntity.ok(roleService.deleteRole(id));
	}
	@GetMapping("/{id}")
	public ResponseEntity<RoleResponseDto> getRole(
			@PathVariable Long id){
		return ResponseEntity.ok(roleService.getRoleById(id));
	}
	@GetMapping
	public ResponseEntity<List<RoleResponseDto>> getAllRoles(){
		return ResponseEntity.ok(roleService.getAllRoles());
	}
	@PostMapping("/{roleId}/users/{userId}")
	public ResponseEntity<ApiResponseDto> assignRole(
			@PathVariable Long roleId,@PathVariable Long userId){
		
		return ResponseEntity.ok(roleService.assignRoleToUser(
				userId,roleId));
	}
	@DeleteMapping("/{roleId}/users/{userId}")
	public ResponseEntity<ApiResponseDto> removeRole(
			@PathVariable Long roleId,@PathVariable Long userId){
		return ResponseEntity.ok(
				roleService.removeRoleFromUser(userId,roleId));
	}
	
	
}
