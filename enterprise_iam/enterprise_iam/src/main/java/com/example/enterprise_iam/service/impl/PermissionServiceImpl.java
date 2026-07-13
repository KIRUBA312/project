package com.example.enterprise_iam.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprise_iam.dto.request.PermissionRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.PermissionResponseDto;
import com.example.enterprise_iam.entity.Permission;
import com.example.enterprise_iam.entity.Role;
import com.example.enterprise_iam.entity.RolePermission;
import com.example.enterprise_iam.exception.ResourceAlreadyExistsException;
import com.example.enterprise_iam.exception.ResourceNotFoundException;
import com.example.enterprise_iam.repository.PermissionRepository;
import com.example.enterprise_iam.repository.RolePermissionRepository;
import com.example.enterprise_iam.repository.RoleRepository;
import com.example.enterprise_iam.service.PermissionService;
import com.example.enterprise_iam.util.MapperUtil;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	@Autowired
	private MapperUtil mapperUtil;
	@Override
	public PermissionResponseDto createPermission(@Valid PermissionRequestDto request) {
		// TODO Auto-generated method stub
		if(permissionRepository.findByName(request.getName()).isPresent()) {
			throw new ResourceAlreadyExistsException("Permission already exists");
		}
		
		Permission permission = new Permission();
		permission.setName(request.getName());
		permission.setDescription(request.getDescription());
		
		permission = permissionRepository.save(permission);
		return mapperUtil.toPermissionResponse(permission);
	}
	@Override
	public PermissionResponseDto updatePermission(Long id, @Valid PermissionRequestDto request) {
		// TODO Auto-generated method stub
		Permission permission = permissionRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Permission not found"));
		permission.setName(request.getName());
		permission.setDescription(request.getDescription());
		
		permission = permissionRepository.save(permission);
		return mapperUtil.toPermissionResponse(permission);
	}
	@Override
	public ApiResponseDto deletePermission(Long id) {
		// TODO Auto-generated method stub
		Permission permission = permissionRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Permission nor found"));
		permissionRepository.delete(permission);
		return new ApiResponseDto(true,"Permission deleted successfully");
	}
	@Override
	public PermissionResponseDto getPermissionById(Long id) {
		// TODO Auto-generated method stub
		Permission permission = permissionRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Permission not found"));
		return mapperUtil.toPermissionResponse(permission);
	}
	@Override
	public List<PermissionResponseDto> getAllPermission() {
		// TODO Auto-generated method stub
		return permissionRepository.findAll().stream()
				.map(mapperUtil::toPermissionResponse)
				.collect(Collectors.toList());
	}
	@Override
	public ApiResponseDto assignPermissionToRole(Long roleId, Long permissionId) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(roleId).orElseThrow(() ->
		new ResourceNotFoundException("Role not found"));
		
		Permission permission = permissionRepository.findById(permissionId)
				.orElseThrow(() ->
				new ResourceNotFoundException("Permission not found"));
		if(rolePermissionRepository.findByRoleAndPermission(role,permission)
				.isPresent()) {
			throw new ResourceAlreadyExistsException(
					"Permission already assigned to role");
		}
		RolePermission rolePermission = new RolePermission();
		rolePermission.setRole(role);
		rolePermission.setPermission(permission);
		
		rolePermissionRepository.save(rolePermission);
		return new ApiResponseDto(true,
				"Permission assigned successfully");
	}
	@Override
	public ApiResponseDto removePermissionFromRole(Long roleId, Long permissionId) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(roleId).orElseThrow(() ->
		new ResourceNotFoundException("Role not found"));
		Permission permission = permissionRepository.findById(permissionId)
				.orElseThrow(() ->
				new ResourceNotFoundException("Permission not found"));
		RolePermission rolePermission = rolePermissionRepository
				.findByRoleAndPermission(role, permission).orElseThrow(()->
				new ResourceNotFoundException("Permission assignment not found"));
		rolePermissionRepository.delete(rolePermission);
		
		return new ApiResponseDto(true,"Permission removed successfully");
	}
	
	
}
