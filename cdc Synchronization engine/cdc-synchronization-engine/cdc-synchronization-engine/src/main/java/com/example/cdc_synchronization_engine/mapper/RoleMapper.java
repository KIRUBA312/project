package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.RoleRequest;
import com.example.cdc_synchronization_engine.dto.RoleResponse;
import com.example.cdc_synchronization_engine.entity.Role;

@Component
public class RoleMapper {

    public Role toEntity(RoleRequest request) {

        if (request == null) {
            return null;
        }

        Role role = new Role();

        role.setRoleName(request.getRoleName());
        role.setDescription(request.getDescription());

        return role;
    }

    public RoleResponse toResponse(Role role) {

        if (role == null) {
            return null;
        }

        RoleResponse response = new RoleResponse();

        response.setId(role.getId());
        response.setRoleName(role.getRoleName());
        response.setDescription(role.getDescription());
        response.setDescription(role.getDescription());
        response.setCreatedAt(role.getCreatedAt());

        return response;
    }

    public void updateEntity(Role role,
                             RoleRequest request) {

        if (role == null || request == null) {
            return;
        }

        role.setRoleName(request.getRoleName());
        role.setDescription(request.getDescription());
    }
}