package com.example.rbacsystem.service;

import java.util.List;


import com.example.rbacsystem.dto.RoleRequestDto;
import com.example.rbacsystem.dto.RoleResponseDto;

public interface RoleService {

	RoleResponseDto createRole(RoleRequestDto dto);

	List<RoleResponseDto> getAllRoles();

	RoleResponseDto getRoleById(Long id);

	RoleResponseDto updateRole(Long id, RoleRequestDto dto);

	void deleteRole(Long id);

}
