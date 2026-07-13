package com.example.enterprise_iam.service;

import java.util.List;

import com.example.enterprise_iam.dto.response.AdminDashboardResponseDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.UserResponseDto;

public interface AdminService {

	AdminDashboardResponseDto getDashboard();

	List<UserResponseDto> getAllUsers();

	UserResponseDto getUserById(Long id);

	ApiResponseDto enableUser(Long id);

	ApiResponseDto disableUser(Long id);

	ApiResponseDto lockUser(Long id);

	ApiResponseDto unlockUser(Long id);

	ApiResponseDto resetFailedAttempts(Long id);

	ApiResponseDto deleteUser(Long id);

}
