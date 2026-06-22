package com.example.user_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user_service.dto.RoleRequestDto;
import com.example.user_service.dto.RoleResponseDto;
import com.example.user_service.entity.Permission;
import com.example.user_service.entity.Role;
import com.example.user_service.exception.ResourceNotFoundException;
import com.example.user_service.repository.PermissionRepository;
import com.example.user_service.repository.RoleRepository;
import com.example.user_service.service.RoleService;

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
		List<Permission> permissions = permissionRepository
				.findAllById(dto.getPermissionIds());
		role.setPermissions(permissions);
		role = roleRepository.save(role);
		return maptoresponse(role);
	}
	@Override
	public RoleResponseDto getRoleById(Long id) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Role Not Found"));
		return maptoresponse(role);
	}
	@Override
	public List<RoleResponseDto> getAllRoles() {
		// TODO Auto-generated method stub
		return roleRepository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	@Override
	public RoleResponseDto updateRole(Long id, RoleRequestDto dto) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Role Not Found"));
		role.setRoleName(dto.getRoleName());
		role.setPermissions(permissionRepository
				.findAllById(dto.getPermissionIds()));
		role = roleRepository.save(role);
		return maptoresponse(role);
	}
	@Override
	public String deleteRole(Long id) {
		// TODO Auto-generated method stub
		roleRepository.deleteById(id);
		return "Role Deleted Successfully";
	}
	
	private RoleResponseDto maptoresponse(Role role) {
		RoleResponseDto dto = new RoleResponseDto();
		
		dto.setId(role.getId());
		dto.setRoleName(role.getRoleName());
		dto.setPermissions(role.getPermissions().stream()
				.map(Permission::getPermissionName)
				.collect(Collectors.toList()));
		
		return dto;
	}
	
	
	
}
