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

import com.example.rbacsystem.dto.RoleRequestDto;
import com.example.rbacsystem.dto.RoleResponseDto;
import com.example.rbacsystem.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@PostMapping
	public ResponseEntity<RoleResponseDto> createRole(
			@RequestBody RoleRequestDto dto){
		
		return ResponseEntity.ok(roleService.createRole(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<RoleResponseDto>> getAllRoles(){
		return ResponseEntity.ok(roleService.getAllRoles());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RoleResponseDto> getRoleById(
			@PathVariable Long id){
		
		return ResponseEntity.ok(roleService.getRoleById(id));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RoleResponseDto> updateRole(
			@PathVariable Long id,
			@RequestBody RoleRequestDto dto){
		
		return ResponseEntity.ok(roleService.updateRole(id, dto));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRole(
			@PathVariable Long id){
		
		roleService.deleteRole(id);
		return ResponseEntity.ok("Role Deleted Successfully");
	}
	
}
