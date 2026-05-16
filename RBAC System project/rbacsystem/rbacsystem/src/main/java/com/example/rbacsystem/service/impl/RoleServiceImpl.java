package com.example.rbacsystem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbacsystem.dto.RoleRequestDto;
import com.example.rbacsystem.dto.RoleResponseDto;
import com.example.rbacsystem.entity.Permission;
import com.example.rbacsystem.entity.Role;
import com.example.rbacsystem.exception.ResourceNotFoundException;
import com.example.rbacsystem.repository.PermissionRepository;
import com.example.rbacsystem.repository.RoleRepository;
import com.example.rbacsystem.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public RoleResponseDto createRole(RoleRequestDto dto) {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setRoleName(dto.getRoleName());
		
		List<Permission> permissionList = new ArrayList<>();
		
		for(Long permissionId : dto.getPermissionIds()) {
			Permission permission = 
					permissionRepository.findById(permissionId)
					.orElseThrow(() ->
					new ResourceNotFoundException("Permission not found"));
			
			permissionList.add(permission);
		}
		role.setPermissions(permissionList);
		Role savedRole = roleRepository.save(role);
		
		return maptoDto(savedRole);
	}

	@Override
	public List<RoleResponseDto> getAllRoles() {
		// TODO Auto-generated method stub
		return roleRepository.findAll()
				.stream().map(this::maptoDto)
				.collect(Collectors.toList());
	}

	@Override
	public RoleResponseDto getRoleById(Long id) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Role Not Found"));
		return maptoDto(role);
	}

	@Override
	public RoleResponseDto updateRole(Long id, RoleRequestDto dto) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Role not found"));
		role.setRoleName(dto.getRoleName());
		
		List<Permission> permissionList = new ArrayList<>();
		
		for(Long permissionId : dto.getPermissionIds()) {
			Permission permission = 
					permissionRepository.findById(permissionId)
					.orElseThrow(() ->
					new ResourceNotFoundException("Permission not found"));
			
			permissionList.add(permission);
		}
		role.setPermissions(permissionList);
		Role updatedRole = roleRepository.save(role);
		return maptoDto(updatedRole);
	}

	@Override
	public void deleteRole(Long id) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Role not found"));
		roleRepository.delete(role);
		
	}
	
	private RoleResponseDto maptoDto(Role role) {
		
		RoleResponseDto dto = new RoleResponseDto();
		dto.setId(role.getId());
		dto.setRoleName(role.getRoleName());
		List<String> permissionNames = role.getPermissions()
				.stream().map(Permission::getPermissionName)
				.collect(Collectors.toList());
		dto.setPermissionName(permissionNames);
		
		return dto;
	}
	
}
