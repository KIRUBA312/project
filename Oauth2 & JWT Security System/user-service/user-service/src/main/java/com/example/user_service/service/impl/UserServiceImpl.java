package com.example.user_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user_service.dto.UserRequestDto;
import com.example.user_service.dto.UserResponseDto;
import com.example.user_service.entity.Role;
import com.example.user_service.entity.Tenant;
import com.example.user_service.entity.User;
import com.example.user_service.exception.ResourceNotFoundException;
import com.example.user_service.repository.RoleRepository;
import com.example.user_service.repository.TenantRepository;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TenantRepository tenantRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserResponseDto createUser(UserRequestDto dto) {
		// TODO Auto-generated method stub
		Tenant tenant = tenantRepository.findById(dto.getTenantId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Tenant Not Found"));
		List<Role> roles = roleRepository.findAllById(dto.getRoleIds());
		User user = new User();
		
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setTenant(tenant);
		user.setRoles(roles);
		user = userRepository.save(user);
		return maptoresponse(user);
	}
	@Override
	public UserResponseDto getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() ->
					new ResourceNotFoundException("User Not Found"));
		return maptoresponse(user);
	}
	@Override
	public List<UserResponseDto> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	@Override
	public UserResponseDto updateUser(Long id, UserRequestDto dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("User Not Found"));
		Tenant tenant = tenantRepository.findById(dto.getTenantId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Tenant Not Found"));
		List<Role> roles = roleRepository.findAllById(dto.getRoleIds());
		
		
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setTenant(tenant);
		user.setRoles(roles);
		user = userRepository.save(user);
		
		return maptoresponse(user);
	}
	@Override
	public String deleteUser(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("User Not Found"));
		userRepository.delete(user);
		return "User Deleted Successfully";
	}
	
	private UserResponseDto maptoresponse(User user) {
		UserResponseDto dto = new UserResponseDto();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setTenantName(user.getTenant().getTenantName());
		dto.setRoles(user.getRoles().stream()
				.map(Role::getRoleName)
				.collect(Collectors.toList()));
		return dto;
	}
	
	
	
}
