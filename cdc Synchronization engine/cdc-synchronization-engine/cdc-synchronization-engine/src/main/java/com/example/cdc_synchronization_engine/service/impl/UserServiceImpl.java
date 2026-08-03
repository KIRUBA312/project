package com.example.cdc_synchronization_engine.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cdc_synchronization_engine.dto.UserRequest;
import com.example.cdc_synchronization_engine.dto.UserResponse;
import com.example.cdc_synchronization_engine.entity.Role;
import com.example.cdc_synchronization_engine.entity.User;
import com.example.cdc_synchronization_engine.entity.UserRole;
import com.example.cdc_synchronization_engine.exception.ErrorCode;
import com.example.cdc_synchronization_engine.exception.ResourceAlreadyExistsException;
import com.example.cdc_synchronization_engine.exception.ResourceNotFoundException;
import com.example.cdc_synchronization_engine.mapper.UserMapper;
import com.example.cdc_synchronization_engine.repository.RoleRepository;
import com.example.cdc_synchronization_engine.repository.UserRepository;
import com.example.cdc_synchronization_engine.repository.UserRoleRepository;
import com.example.cdc_synchronization_engine.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserRoleRepository userRoleRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse createUser(UserRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {

            throw new ResourceAlreadyExistsException(
                    ErrorCode.RESOURCE_ALREADY_EXISTS,
                    "Username already exists.");
        }

        if (userRepository.existsByEmail(request.getEmail())) {

            throw new ResourceAlreadyExistsException(
                    ErrorCode.RESOURCE_ALREADY_EXISTS,
                    "Email already exists.");
        }

//        Role role = roleRepository.findByRoleName(request.getRoleName())
//                .orElseThrow(() ->
//                        new ResourceNotFoundException(
//                                ErrorCode.RESOURCE_NOT_FOUND,
//                                "Role not found."));

        User user = userMapper.toEntity(request);

        user.setPassword(
                passwordEncoder.encode(request.getPassword()));

        user.setEnabled(true);

        User savedUser = userRepository.save(user);

        UserRole userRole = new UserRole();

        userRole.setUser(savedUser);


        userRoleRepository.save(userRole);

        UserResponse response =
                userMapper.toResponse(savedUser);

        List<String> roles = new ArrayList<>();

        response.setRoles(roles);

        return response;
    }

    @Override
    public UserResponse updateUser(
            Long id,
            UserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                ErrorCode.RESOURCE_NOT_FOUND,
                                "User not found."));

        user.setUsername(request.getUsername());

        user.setEmail(request.getEmail());

        user.setEnabled(request.getEnabled());

        if (request.getPassword() != null &&
                !request.getPassword().isBlank()) {

            user.setPassword(
                    passwordEncoder.encode(
                            request.getPassword()));
        }

        User updatedUser = userRepository.save(user);

        UserResponse response = userMapper
        		.toResponse(updatedUser);

        List<UserRole> userRoles = userRoleRepository
        		.findByUserId(id);

        List<String> roles = new ArrayList<>();

        for (UserRole ur : userRoles) {

            roles.add( ur.getRole().getRoleName());
        }

        response.setRoles(roles);

        return response;
    }

    @Override
    public void deleteUser(Long id) {

        User user =
                userRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ErrorCode.RESOURCE_NOT_FOUND,
                                        "User not found."));

        userRoleRepository.deleteById(id);

        userRepository.delete(user);
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user =
                userRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        ErrorCode.RESOURCE_NOT_FOUND,
                                        "User not found."));

        UserResponse response = userMapper.toResponse(user);

        List<UserRole> userRoles = userRoleRepository
        		.findByUserId(id);

        List<String> roles = new ArrayList<>();

        for (UserRole ur : userRoles) {

            roles.add( ur.getRole().getRoleName());
        }

        response.setRoles(roles);

        return response;
    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> users =
                userRepository.findAll();

        List<UserResponse> responses = new ArrayList<>();

        for (User user : users) {

            UserResponse response = userMapper.toResponse(user);
            List<UserRole> userRoles = userRoleRepository
            		.findByUserId( user.getId());

            List<String> roles = new ArrayList<>();
            for (UserRole ur : userRoles) {

                roles.add(
                        ur.getRole().getRoleName());
            }

            response.setRoles(roles);

            responses.add(response);
        }

        return responses;
    }

}