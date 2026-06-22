package com.example.user_service.service;

import java.util.List;

import com.example.user_service.dto.RoleRequestDto;
import com.example.user_service.dto.RoleResponseDto;

public interface RoleService {

	RoleResponseDto createRole(RoleRequestDto dto);

	RoleResponseDto getRoleById(Long id);

	List<RoleResponseDto> getAllRoles();

	RoleResponseDto updateRole(Long id, RoleRequestDto dto);

	String deleteRole(Long id);

}
