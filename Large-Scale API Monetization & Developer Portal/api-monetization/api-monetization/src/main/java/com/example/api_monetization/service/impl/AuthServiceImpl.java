package com.example.api_monetization.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api_monetization.dto.auth.LoginRequest;
import com.example.api_monetization.dto.auth.LoginResponse;
import com.example.api_monetization.dto.auth.RegisterRequest;
import com.example.api_monetization.dto.user.UserResponse;
import com.example.api_monetization.entity.Role;
import com.example.api_monetization.entity.User;
import com.example.api_monetization.entity.UserRole;
import com.example.api_monetization.enums.AccountStatus;
import com.example.api_monetization.exception.ResourceAlreadyExistsException;
import com.example.api_monetization.exception.ResourceNotFoundException;
import com.example.api_monetization.mapper.UserMapper;
import com.example.api_monetization.repository.RoleRepository;
import com.example.api_monetization.repository.UserRepository;
import com.example.api_monetization.repository.UserRoleRepository;
import com.example.api_monetization.security.jwt.JwtTokenProvider;
import com.example.api_monetization.service.AuthService;

import jakarta.validation.Valid;

@Service
@Transactional
public class AuthServiceImpl implements AuthService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserResponse register(@Valid RegisterRequest request) {
		// TODO Auto-generated method stub
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new ResourceAlreadyExistsException(
					"User already exists with email : "
			+request.getEmail());
		}
		Role developerRole = roleRepository.findByRoleName("ROLE_DEVELOPER")
				.orElseThrow(() ->
				new ResourceNotFoundException("ROLE_DEVELOPER not found"));
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setPhone(request.getPhone());
		user.setAccountStatus(AccountStatus.ACTIVE);
		user.setEmailVerified(false);
		user.setMfaEnabled(false);
		user.setFailedLoginAttempts(0);
		user.setAccountLocked(false);
		User savedUser = userRepository.save(user);
		
		UserRole userRole = new UserRole();
		userRole.setUser(savedUser);
		userRole.setRole(developerRole);
		userRoleRepository.save(userRole);
		savedUser.getUserRoles().add(userRole);
		return userMapper.toResponse(savedUser);
	}

	@Override
	public LoginResponse login(@Valid LoginRequest request) {
		// TODO Auto-generated method stub
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(), 
						request.getPassword()));
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(()->
				new ResourceNotFoundException("User not found"));
		String token = jwtTokenProvider.generateToken(user.getEmail());
		LoginResponse response = new LoginResponse();
		response.setAccessToken(token);
		response.setTokenType("Bearer");
		response.setUser(userMapper.toResponse(user));
		return response;
	}
	
}
