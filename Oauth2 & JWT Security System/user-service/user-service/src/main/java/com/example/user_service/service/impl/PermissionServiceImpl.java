package com.example.user_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user_service.dto.PermissionRequestDto;
import com.example.user_service.dto.PermissionResponseDto;
import com.example.user_service.entity.Permission;
import com.example.user_service.exception.ResourceNotFoundException;
import com.example.user_service.repository.PermissionRepository;
import com.example.user_service.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionRepository repository;

	@Override
	public PermissionResponseDto createPermission(
			PermissionRequestDto dto) {
		// TODO Auto-generated method stub
		Permission permission = new Permission();
		permission.setPermissionName(dto.getPermissionName());
		permission = repository.save(permission);
		return maptoresponse(permission);
	}

	@Override
	public PermissionResponseDto getPermissionById(Long id) {
		// TODO Auto-generated method stub
		Permission permission = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Permission Not Found"));
		return maptoresponse(permission);
	}

	@Override
	public List<PermissionResponseDto> getAllPermissions() {
		// TODO Auto-generated method stub
		return repository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}

	@Override
	public PermissionResponseDto updatePermission(Long id, 
			PermissionRequestDto dto) {
		// TODO Auto-generated method stub
		Permission permission = repository.findById(id)
				.orElseThrow(() -> new 
						ResourceNotFoundException("Permission Not Found"));
		permission.setPermissionName(dto.getPermissionName());
		permission = repository.save(permission);
		return maptoresponse(permission);
	}

	@Override
	public String deletePermission(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
		return "Permission Deleted Successfully";
	}
	
	private PermissionResponseDto maptoresponse(Permission permission) {
		PermissionResponseDto dto = new PermissionResponseDto();
		dto.setId(permission.getId());
		dto.setPermissionName(permission
				.getPermissionName());
		return dto;
	}
	
}
