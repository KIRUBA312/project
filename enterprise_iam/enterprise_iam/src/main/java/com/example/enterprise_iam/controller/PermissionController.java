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

import com.example.enterprise_iam.dto.request.PermissionRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.PermissionResponseDto;
import com.example.enterprise_iam.service.PermissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@PostMapping
	public ResponseEntity<PermissionResponseDto> createPermission(
			@Valid @RequestBody PermissionRequestDto request){
		
		return ResponseEntity.ok(permissionService.createPermission
				(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PermissionResponseDto> updatePermission(
			@PathVariable Long id,@Valid 
			@RequestBody PermissionRequestDto request){
		return ResponseEntity.ok(permissionService.updatePermission(
				id,request));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseDto> deletePermission(
			@PathVariable Long id){
		return ResponseEntity.ok(permissionService.deletePermission(id));
	}
	@GetMapping("/{id}")
	public ResponseEntity<PermissionResponseDto> getPermission(
			@PathVariable Long id){
		return ResponseEntity.ok(permissionService.getPermissionById(id));
	}
	@GetMapping
	public ResponseEntity<List<PermissionResponseDto>> getAllPermissions(){
		return ResponseEntity.ok(permissionService.getAllPermission());
	}
	@PostMapping("/{permissionId}/roles/{roleId}")
	public ResponseEntity<ApiResponseDto> assignPermission(
			@PathVariable Long roleId,@PathVariable Long permissionId){
		return ResponseEntity.ok(permissionService
				.assignPermissionToRole(roleId, permissionId));
	}
	@DeleteMapping("/{permissionId}/roles/{roleId}")
	public ResponseEntity<ApiResponseDto> removePermission(
			@PathVariable Long roleId,@PathVariable Long permissionId){
		return ResponseEntity.ok(permissionService
				.removePermissionFromRole(roleId, permissionId));
	}
	
}
