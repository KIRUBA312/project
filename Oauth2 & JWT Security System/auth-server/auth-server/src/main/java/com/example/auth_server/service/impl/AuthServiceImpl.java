package com.example.auth_server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_server.dto.LoginRequestDto;
import com.example.auth_server.dto.LoginResponseDto;
import com.example.auth_server.dto.RefreshTokenRequestDto;
import com.example.auth_server.dto.RefreshTokenResponseDto;
import com.example.auth_server.dto.UserRequestDto;
import com.example.auth_server.dto.UserResponseDto;
import com.example.auth_server.entity.RefreshToken;
import com.example.auth_server.entity.Role;
import com.example.auth_server.entity.Tenant;
import com.example.auth_server.entity.User;
import com.example.auth_server.exception.AuthenticationFailedException;
import com.example.auth_server.exception.TenantNotFoundException;
import com.example.auth_server.repository.RoleRepository;
import com.example.auth_server.repository.TenantRepository;
import com.example.auth_server.repository.UserRepository;
import com.example.auth_server.security.JwtUtil;
import com.example.auth_server.service.AuditService;
import com.example.auth_server.service.AuthService;
import com.example.auth_server.service.RefreshTokenService;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TenantRepository tenantRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private RefreshTokenService refreshTokenService;
	@Autowired
	private AuditService auditService;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public LoginResponseDto login(LoginRequestDto dto) {
		// TODO Auto-generated method stub
		Tenant tenant = tenantRepository.findByTenantCode(
				dto.getTenantCode()).orElseThrow(() ->
				new TenantNotFoundException("Tenant not found"));
		User user = userRepository.findByUsernameAndTenant(dto.getUsername(), tenant)
				.orElseThrow(() ->
				new AuthenticationFailedException("Invalid username"));
		if(!passwordEncoder.matches(dto.getPassword(),
				user.getPassword())) {
			auditService.logEvent(user.getUsername(), tenant.getId(), "LOGIN_FAILED", "127.0.0.1");
			throw new AuthenticationFailedException("Invalid password");
		}
		List<String> roles = user.getRoles().stream()
				.map(Role::getRoleName)
				.collect(Collectors.toList());
		String accessToken = jwtUtil.generateToken(
				user.getUsername(),tenant.getTenantName(),roles);
		RefreshToken refreshToken = 
				refreshTokenService.createRefreshToken(user);
		auditService.logEvent(user.getUsername(),tenant.getId(),
				"LOGIN_SUCCESS","127.0.0.1");
		LoginResponseDto response = new LoginResponseDto();
		response.setAccessToken(accessToken);
		response.setRefreshToken(refreshToken.getToken());
		response.setTokenType("Bearer");
		return response;
	}
	@Override
	public UserResponseDto register(UserRequestDto dto) {
		// TODO Auto-generated method stub
		Tenant tenant = tenantRepository
				.findById(dto.getTenantId())
				.orElseThrow(() ->
				new TenantNotFoundException(
						"Tenant not found"));
		if(userRepository.existsByUsername(dto.getUsername())) {
			throw new AuthenticationFailedException(
					"Username already exists");
		}
		
		List<Role> roles = roleRepository
				.findAllById(dto.getRoleIds());
		if (roles.size() != dto.getRoleIds().size()) {
			throw new RuntimeException(
					"One or more roles not found");
		}
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(
				dto.getPassword()));
		user.setTenant(tenant);
		user.setRoles(new ArrayList<>(roles));
		user = userRepository.save(user);
		auditService.logEvent(user.getUsername(), 
				tenant.getId(),
				"USER_REGISTERED",
				"127.0.0.1");
		UserResponseDto response = new UserResponseDto();
		response.setId(user.getId());
		response.setUsername(user.getUsername());
		response.setTenantName(tenant.getTenantName());
		response.setRoles(roles.stream()
				.map(Role::getRoleName)
				.collect(Collectors.toList()));
		return response;
	}
	@Override
	public RefreshTokenResponseDto refreshToken(
			RefreshTokenRequestDto dto) {
		// TODO Auto-generated method stub
		RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(
				dto.getRefreshToken());
		User user = refreshToken.getUser();
		List<String> roles = user.getRoles().stream()
				.map(Role::getRoleName).collect(Collectors.toList());
		String accessToken =
				jwtUtil.generateToken(user.getUsername(), 
						user.getTenant().getTenantName(), user.getRoles()
						.stream().map(Role::getRoleName).toList());
		RefreshTokenResponseDto response = new RefreshTokenResponseDto();
		response.setAccessToken(accessToken);
		return response;
	}
	@Override
	public String logout(String refreshToken) {
		// TODO Auto-generated method stub
		refreshTokenService.revokeRefreshToken(refreshToken);
		return "Logout successful";
	}
	

}
