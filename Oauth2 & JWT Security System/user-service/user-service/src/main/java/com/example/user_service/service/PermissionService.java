package com.example.user_service.service;

import java.util.List;

import com.example.user_service.dto.PermissionRequestDto;
import com.example.user_service.dto.PermissionResponseDto;

public interface PermissionService {

	PermissionResponseDto createPermission(PermissionRequestDto dto);

	PermissionResponseDto getPermissionById(Long id);

	List<PermissionResponseDto> getAllPermissions();

	PermissionResponseDto updatePermission(Long id, PermissionRequestDto dto);

	String deletePermission(Long id);

}
