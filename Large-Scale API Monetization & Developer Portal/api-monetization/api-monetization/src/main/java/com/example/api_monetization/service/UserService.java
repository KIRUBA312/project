package com.example.api_monetization.service;

import java.util.List;

import com.example.api_monetization.dto.user.UserRequest;
import com.example.api_monetization.dto.user.UserResponse;
import com.example.api_monetization.enums.AccountStatus;

import jakarta.validation.Valid;

public interface UserService {

	List<UserResponse> getAllUsers();

	UserResponse getUser(Long id);

	UserResponse updateUser(Long id, @Valid UserRequest request);

	void updateStatus(Long id, AccountStatus active);

}
