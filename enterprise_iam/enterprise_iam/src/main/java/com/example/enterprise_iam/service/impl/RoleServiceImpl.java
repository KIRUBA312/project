package com.example.enterprise_iam.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprise_iam.dto.request.RoleRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.RoleResponseDto;
import com.example.enterprise_iam.entity.Role;
import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.entity.UserRole;
import com.example.enterprise_iam.exception.ResourceAlreadyExistsException;
import com.example.enterprise_iam.exception.ResourceNotFoundException;
import com.example.enterprise_iam.repository.RoleRepository;
import com.example.enterprise_iam.repository.UserRepository;
import com.example.enterprise_iam.repository.UserRoleRepository;
import com.example.enterprise_iam.service.RoleService;
import com.example.enterprise_iam.util.MapperUtil;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private MapperUtil mapperUtil;
	@Override
	public RoleResponseDto createRole(@Valid RoleRequestDto request) {
		// TODO Auto-generated method stub
		if(roleRepository.findByName(request.getName()).isPresent()) {
			throw new ResourceAlreadyExistsException("Role already exists");
		}
		Role role = new Role();
		role.setName(request.getName());
		role.setName(request.getName());
		role.setDescription(request.getDescription());
		
		role = roleRepository.save(role);
		return mapperUtil.toRoleResponse(role);
	}
	@Override
	public RoleResponseDto updateRole(Long id, @Valid 
			RoleRequestDto request) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Role not found"));
		role.setName(request.getName());
		role.setDescription(request.getDescription());
		
		role = roleRepository.save(role);
		return mapperUtil.toRoleResponse(role);
	}
	@Override
	public ApiResponseDto deleteRole(Long id) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Role not found"));
		roleRepository.delete(role);
		return new ApiResponseDto(true,"Role deleted successfully");
	}
	@Override
	public RoleResponseDto getRoleById(Long id) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Role not found"));
		return mapperUtil.toRoleResponse(role);
	}
	@Override
	public List<RoleResponseDto> getAllRoles() {
		// TODO Auto-generated method stub
		return roleRepository.findAll().stream()
				.map(mapperUtil::toRoleResponse)
				.collect(Collectors.toList());
	}
	@Override
	public ApiResponseDto assignRoleToUser(Long userId, Long roleId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(() ->
		new ResourceNotFoundException("User not found"));
		Role role = roleRepository.findById(roleId).orElseThrow(() ->
		new ResourceNotFoundException("Role not found"));
		if(userRoleRepository.findByUserAndRole(user,role).isPresent()) {
			throw new ResourceAlreadyExistsException("Role already assigned to user");
		}
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		userRoleRepository.save(userRole);
		return new ApiResponseDto(true,"Role assigned successfully");
	}
	@Override
	public ApiResponseDto removeRoleFromUser(Long userId, Long roleId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(() ->
		new ResourceNotFoundException("User not found"));
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() ->
		new ResourceNotFoundException("Role not found"));
		UserRole userRole = userRoleRepository.findByUserAndRole(user, role)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Role assignment not found"));
		userRoleRepository.delete(userRole);
		return new ApiResponseDto(true,"Role removed successfully");
	}
	
	
}
