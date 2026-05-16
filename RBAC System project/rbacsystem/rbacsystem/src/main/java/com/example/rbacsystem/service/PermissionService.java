package com.example.rbacsystem.service;

import java.util.List;


import com.example.rbacsystem.dto.PermissionRequestDto;
import com.example.rbacsystem.dto.PermissionResponseDto;

public interface PermissionService {

	PermissionResponseDto createPermission(PermissionRequestDto dto);

	List<PermissionResponseDto> getAllPermissions();

	PermissionResponseDto getPermissionById(Long id);

	PermissionResponseDto updatePermission(Long id, PermissionRequestDto dto);

	void deletePermission(Long id);

}
