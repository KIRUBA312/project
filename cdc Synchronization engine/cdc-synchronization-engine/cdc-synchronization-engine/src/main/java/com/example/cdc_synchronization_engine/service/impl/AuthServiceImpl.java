package com.example.cdc_synchronization_engine.service.impl;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cdc_synchronization_engine.dto.LoginRequest;
import com.example.cdc_synchronization_engine.dto.LoginResponse;
import com.example.cdc_synchronization_engine.dto.RegisterRequest;
import com.example.cdc_synchronization_engine.dto.RegisterResponse;
import com.example.cdc_synchronization_engine.entity.Role;
import com.example.cdc_synchronization_engine.entity.User;
import com.example.cdc_synchronization_engine.entity.UserRole;
import com.example.cdc_synchronization_engine.exception.ErrorCode;
import com.example.cdc_synchronization_engine.exception.ResourceAlreadyExistsException;
import com.example.cdc_synchronization_engine.exception.ResourceNotFoundException;
import com.example.cdc_synchronization_engine.exception.*;
import com.example.cdc_synchronization_engine.mapper.UserMapper;
import com.example.cdc_synchronization_engine.repository.RoleRepository;
import com.example.cdc_synchronization_engine.repository.UserRepository;
import com.example.cdc_synchronization_engine.repository.UserRoleRepository;
import com.example.cdc_synchronization_engine.security.JwtTokenProvider;
import com.example.cdc_synchronization_engine.service.AuthService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class AuthServiceImpl implements AuthService{

	private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    public AuthServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserRoleRepository userRoleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider, UserMapper userMapper) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
		this.userMapper = userMapper;
    }

	@Override
	public RegisterResponse register( RegisterRequest request) {
		// TODO Auto-generated method stub
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResourceAlreadyExistsException(
            		ErrorCode.RESOURCE_ALREADY_EXISTS,
                    "Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException(
            		ErrorCode.RESOURCE_ALREADY_EXISTS,
                    "Email already exists");
        }

        Role role = roleRepository.findByRoleName(request.getRoleName())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                        		ErrorCode.RESOURCE_NOT_FOUND,
                                "Role not found"));

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);

        User savedUser = userRepository.save(user);

        UserRole userRole = new UserRole();

        userRole.setUser(savedUser);
        userRole.setRole(role);

        userRoleRepository.save(userRole);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                role.getRoleName(),
                "User registered successfully");
    
	}

	@Override
	public LoginResponse login( LoginRequest request) {
		// TODO Auto-generated method stub

        try {

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(),
                                    request.getPassword()));

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            String token =
                    jwtTokenProvider.generateToken(authentication);

            User user =
                    userRepository.findByUsername(request.getUsername())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            ErrorCode.RESOURCE_NOT_FOUND,
                                            "User not found."));

            return new LoginResponse(
                    token,
                    "Bearer",
                    null,
                    user.getUsername(),
                    userMapper.toResponse(user)
            );

        } catch (Exception ex) {
            throw new AuthenticationException(
                    ErrorCode.AUTHENTICATION_FAILED,
                    "Invalid username or password.");
        }
	}

	@Override
	public RegisterResponse getCurrentUser() {
		// TODO Auto-generated method stub
		String username =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository.findByUsername(username)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ErrorCode.RESOURCE_NOT_FOUND,
                                        "User not found."));

        List<UserRole> roles = userRoleRepository
        		.findByUserId(user.getId());

        String roleName = "";

        if (!roles.isEmpty()) {
            roleName = roles.get(0).getRole().getRoleName();
        }

        return new RegisterResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                roleName,
                "Current user fetched successfully."
        );
	}

}
