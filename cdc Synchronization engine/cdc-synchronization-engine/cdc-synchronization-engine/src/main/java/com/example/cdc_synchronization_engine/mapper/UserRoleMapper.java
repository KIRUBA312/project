package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.UserRoleResponse;
import com.example.cdc_synchronization_engine.entity.UserRole;

@Component
public class UserRoleMapper {

    public UserRoleResponse toResponse(UserRole userRole) {

        if (userRole == null) {
            return null;
        }

        UserRoleResponse response = new UserRoleResponse();

        response.setId(userRole.getId());

        if (userRole.getUser() != null) {

            response.setUserId(userRole.getUser().getId());
            response.setUsername(userRole.getUser().getUsername());
        }

        if (userRole.getRole() != null) {

            response.setRoleId(userRole.getRole().getId());
            response.setRoleName(userRole.getRole().getRoleName());
        }

        return response;
    }
}