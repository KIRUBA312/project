package com.example.rbacsystem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.rbacsystem.dto.UserRequestDto;
import com.example.rbacsystem.dto.UserResponseDto;
import com.example.rbacsystem.entity.Role;
import com.example.rbacsystem.entity.User;
import com.example.rbacsystem.exception.ResourceNotFoundException;
import com.example.rbacsystem.repository.RoleRepository;
import com.example.rbacsystem.repository.UserRepository;
import com.example.rbacsystem.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserResponseDto createUser(UserRequestDto dto) {
		// TODO Auto-generated method stub
		User user = new User();
		
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setEmail(dto.getEmail());
		
		List<Role> roleList = new ArrayList<Role>();
		for(Long roleId : dto.getRoleIds()) {
			Role role = roleRepository.findById(roleId)
					.orElseThrow(() ->
					new RuntimeException("Role not found"));
			
			roleList.add(role);
		}
		user.setRoles(roleList);
		
		User savedUser = userRepository.save(user);
		
		return maptoDto(savedUser);
	}

	@Override
	public List<UserResponseDto> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll()
				.stream().map(this::maptoDto)
				.collect(Collectors.toList());
	}

	@Override
	public UserResponseDto getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("User not found"));
		return maptoDto(user);
	}

	@Override
	public UserResponseDto updateUser(Long id, UserRequestDto dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("User Not Found"));
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setEmail(dto.getEmail());
		
		List<Role> roleList = new ArrayList<>();
		for(Long roleId : dto.getRoleIds()) {
			Role role = roleRepository.findById(roleId)
					.orElseThrow(() ->
					new ResourceNotFoundException("Role Not Found"));
			
			roleList.add(role);
		}
		
		user.setRoles(roleList);
		User updatedUser = userRepository.save(user);
		
		return maptoDto(updatedUser);
		
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user Not Found"));
		userRepository.delete(user);
		
		
	}
	
	private UserResponseDto maptoDto(User user) {
		UserResponseDto dto = new UserResponseDto();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		List<String> roleNames = user.getRoles()
				.stream().map(Role::getRoleName)
				.collect(Collectors.toList());
		dto.setRoles(roleNames);
		
		return dto;
	}
	
	

}
