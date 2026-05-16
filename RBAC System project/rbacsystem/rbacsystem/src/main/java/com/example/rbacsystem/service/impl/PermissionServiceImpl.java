package com.example.rbacsystem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbacsystem.dto.PermissionRequestDto;
import com.example.rbacsystem.dto.PermissionResponseDto;
import com.example.rbacsystem.entity.Permission;
import com.example.rbacsystem.exception.ResourceNotFoundException;
import com.example.rbacsystem.repository.PermissionRepository;
import com.example.rbacsystem.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public PermissionResponseDto createPermission(
			PermissionRequestDto dto) {
		// TODO Auto-generated method stub
		Permission permission = new Permission();
		permission.setPermissionName(dto.getPermissionName());
		Permission savedPermission = permissionRepository.save(permission);
		return maptoDto(savedPermission);
	}

	@Override
	public List<PermissionResponseDto> getAllPermissions() {
		// TODO Auto-generated method stub
		return permissionRepository.findAll().stream()
				.map(this::maptoDto)
				.collect(Collectors.toList());
	}

	@Override
	public PermissionResponseDto getPermissionById(Long id) {
		// TODO Auto-generated method stub
		Permission permission = 
				permissionRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Permission not found"));
		return maptoDto(permission);
	}

	@Override
	public PermissionResponseDto updatePermission(Long id, PermissionRequestDto dto) {
		// TODO Auto-generated method stub
		 Permission permission = 
				 permissionRepository.findById(id)
				 .orElseThrow(() ->
				 new ResourceNotFoundException("Permission not found"));
		 permission.setPermissionName(dto.getPermissionName());
		 Permission updatedPermission = permissionRepository.save(permission);
		 return maptoDto(updatedPermission);

	}

	@Override
	public void deletePermission(Long id) {
		// TODO Auto-generated method stub
		Permission permission = permissionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Permission Not Found"));
		permissionRepository.delete(permission);
	}
	
	private PermissionResponseDto maptoDto(
			Permission permission) {
		PermissionResponseDto dto = new PermissionResponseDto();
		dto.setId(permission.getId());
		dto.setPermissionName(permission.getPermissionName());
		
		return dto;
	}
	
	

}
