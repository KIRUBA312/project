package com.example.auth_server.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_server.dto.UserRequestDto;
import com.example.auth_server.dto.UserResponseDto;
import com.example.auth_server.entity.Role;
import com.example.auth_server.entity.Tenant;
import com.example.auth_server.entity.User;
import com.example.auth_server.exception.TenantNotFoundException;
import com.example.auth_server.repository.RoleRepository;
import com.example.auth_server.repository.TenantRepository;
import com.example.auth_server.repository.UserRepository;
import com.example.auth_server.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TenantRepository tenantRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UserResponseDto createUser(UserRequestDto dto) {
		// TODO Auto-generated method stub
		Tenant tenant = tenantRepository.findById(dto.getTenantId())
				.orElseThrow(() -> new TenantNotFoundException("Tenant not found"));
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setTenant(tenant);
		List<Role> roles = roleRepository.findAllById(dto.getRoleIds());
		user.setRoles(roles);
		user = userRepository.save(user);
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
	public UserResponseDto getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow();
		return maptoresponse(user);
	}
	@Override
	public UserResponseDto updateUser(Long id, UserRequestDto dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow();
		user.setUsername(dto.getUsername());
		if (dto.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(dto.getPassword()));
		}
		List<Role> roles = roleRepository.findAllById(dto.getRoleIds());
		user.setRoles(roles);
		user = userRepository.save(user);
		return maptoresponse(user);
	}
	
	@Override
	public String deleteUser(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
		return "User deleted successfully";
	}
	private UserResponseDto maptoresponse(User user) {
		// TODO Auto-generated method stub
		UserResponseDto dto = new UserResponseDto();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setTenantName(user.getTenant().getTenantName());
		dto.setRoles(user.getRoles().stream().map(Role::getRoleName)
				.collect(Collectors.toList()));
		return dto;
	}
}
