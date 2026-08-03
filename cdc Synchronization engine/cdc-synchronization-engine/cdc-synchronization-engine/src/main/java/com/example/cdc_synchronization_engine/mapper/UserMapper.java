package com.example.cdc_synchronization_engine.mapper;

import org.springframework.stereotype.Component;

import com.example.cdc_synchronization_engine.dto.UserRequest;
import com.example.cdc_synchronization_engine.dto.UserResponse;
import com.example.cdc_synchronization_engine.entity.User;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {

        if (request == null) {
            return null;
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setEnabled(request.getEnabled());

        return user;
    }

    public UserResponse toResponse(User user) {

        if (user == null) {
            return null;
        }

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setEnabled(user.getEnabled());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }

    public void updateEntity(User user,
                             UserRequest request) {

        if (user == null || request == null) {
            return;
        }

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        if (request.getPassword() != null &&
                !request.getPassword().isBlank()) {

            user.setPassword(request.getPassword());
        }

        user.setEnabled(request.getEnabled());
    }
}