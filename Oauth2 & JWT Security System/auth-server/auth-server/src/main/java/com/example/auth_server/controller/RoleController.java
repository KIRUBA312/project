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

import com.example.auth_server.entity.Role;
import com.example.auth_server.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@PostMapping
	public ResponseEntity<Role> createRole(@RequestBody Role role){
		return ResponseEntity.ok(roleService.createRole(role));
	}
	@GetMapping
	public ResponseEntity<List<Role>> getAllRoles(){
		return ResponseEntity.ok(roleService.getAllRoles());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Role> getRolesById(@PathVariable Long id){
		return ResponseEntity.ok(roleService.getRoleById(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<Role> updateRole(@PathVariable Long id,
			@RequestBody Role role){
		return ResponseEntity.ok(roleService
				.updateRole(id, role));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRole(@PathVariable Long id){
		return ResponseEntity.ok(roleService.deleteMapping(id));
	}
	
}
