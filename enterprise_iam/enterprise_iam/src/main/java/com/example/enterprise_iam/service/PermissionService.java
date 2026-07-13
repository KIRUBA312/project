package com.example.enterprise_iam.service;

import java.util.List;

import com.example.enterprise_iam.dto.request.PermissionRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.PermissionResponseDto;

import jakarta.validation.Valid;

public interface PermissionService {

	PermissionResponseDto createPermission(@Valid PermissionRequestDto request);

	PermissionResponseDto updatePermission(Long id, @Valid PermissionRequestDto request);

	ApiResponseDto deletePermission(Long id);

	PermissionResponseDto getPermissionById(Long id);

	List<PermissionResponseDto> getAllPermission();

	ApiResponseDto assignPermissionToRole(Long roleId, Long permissionId);

	ApiResponseDto removePermissionFromRole(Long roleId, Long permissionId);

}
