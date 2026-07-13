package com.example.enterprise_iam.service;

import java.util.List;

import com.example.enterprise_iam.dto.request.RoleRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.RoleResponseDto;

import jakarta.validation.Valid;

public interface RoleService {

	RoleResponseDto createRole(@Valid RoleRequestDto request);

	RoleResponseDto updateRole(Long id, @Valid RoleRequestDto request);

	ApiResponseDto deleteRole(Long id);

	RoleResponseDto getRoleById(Long id);

	List<RoleResponseDto> getAllRoles();

	ApiResponseDto assignRoleToUser(Long userId, Long roleId);

	ApiResponseDto removeRoleFromUser(Long userId, Long roleId);

}
