package com.example.cdc_synchronization_engine.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.dto.RoleRequest;
import com.example.cdc_synchronization_engine.dto.RoleResponse;
import com.example.cdc_synchronization_engine.dto.UserRoleRequest;
import com.example.cdc_synchronization_engine.dto.UserRoleResponse;
import com.example.cdc_synchronization_engine.entity.Role;
import com.example.cdc_synchronization_engine.entity.User;
import com.example.cdc_synchronization_engine.entity.UserRole;
import com.example.cdc_synchronization_engine.exception.ErrorCode;
import com.example.cdc_synchronization_engine.exception.ResourceAlreadyExistsException;
import com.example.cdc_synchronization_engine.exception.ResourceNotFoundException;
import com.example.cdc_synchronization_engine.mapper.RoleMapper;
import com.example.cdc_synchronization_engine.mapper.UserRoleMapper;
import com.example.cdc_synchronization_engine.repository.RoleRepository;
import com.example.cdc_synchronization_engine.repository.UserRepository;
import com.example.cdc_synchronization_engine.repository.UserRoleRepository;
import com.example.cdc_synchronization_engine.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;

    public RoleServiceImpl(
            RoleRepository roleRepository,
            UserRepository userRepository,
            UserRoleRepository userRoleRepository, 
            UserRoleMapper userRoleMapper, 
            RoleMapper roleMapper) {

        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleMapper = roleMapper;
		this.userRoleMapper = userRoleMapper;
    }

    @Override
    public RoleResponse createRole(RoleRequest request) {

        if (roleRepository.existsByRoleName(request.getRoleName())) {

            throw new ResourceAlreadyExistsException(
                    ErrorCode.RESOURCE_ALREADY_EXISTS,
                    "Role already exists: "
                            + request.getRoleName());
        }

        Role role = new Role();

        role.setRoleName(request.getRoleName());
        role.setDescription(request.getDescription());

        Role savedRole = roleRepository.save(role);

        return roleMapper.toResponse(savedRole);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponse)
                .toList();
    }

    @Override
    public UserRoleResponse assignRole(
            UserRoleRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                ErrorCode.RESOURCE_NOT_FOUND,
                                "User not found with ID: "
                                        + request.getUserId()));

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                ErrorCode.RESOURCE_NOT_FOUND,
                                "Role not found with ID: "
                                        + request.getRoleId()));

        if (userRoleRepository.existsByUserIdAndRoleId(
                request.getUserId(),
                request.getRoleId())) {

            throw new ResourceAlreadyExistsException(
                    ErrorCode.RESOURCE_ALREADY_EXISTS,
                    "Role '" + role.getRoleName()
                            + "' is already assigned to user '"
                            + user.getUsername() + "'.");
        }

        UserRole userRole = new UserRole();

        userRole.setUser(user);
        userRole.setRole(role);

        UserRole savedUserRole =
                userRoleRepository.save(userRole);

        return userRoleMapper.toResponse(savedUserRole);
    }


}