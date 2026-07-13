package com.example.enterprise_iam.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprise_iam.dto.response.AdminDashboardResponseDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.UserResponseDto;
import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.exception.ResourceNotFoundException;
import com.example.enterprise_iam.repository.PermissionRepository;
import com.example.enterprise_iam.repository.RoleRepository;
import com.example.enterprise_iam.repository.UserRepository;
import com.example.enterprise_iam.repository.UserSessionRepository;
import com.example.enterprise_iam.service.AdminService;
import com.example.enterprise_iam.service.AuditLogService;
import com.example.enterprise_iam.util.MapperUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private UserSessionRepository userSessionRepository;
	@Autowired
	private MapperUtil mapperUtil;
	@Autowired
	private AuditLogService auditLogService;
	
    @Override
    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(mapperUtil::toUserResponse)
                .collect(Collectors.toList());
    }

    
    @Override
    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                        		"User not found"));

        return mapperUtil.toUserResponse(user);
    }
    
    @Override
    public ApiResponseDto enableUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setEnabled(true);
        userRepository.save(user);

        auditLogService.logAction(
                "ENABLE_USER",
                "User",
                user.getId(),
                user.getId(),
                "User account enabled");

        return new ApiResponseDto(
                true,
                "User enabled successfully");
    }

    
    @Override
    public ApiResponseDto disableUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setEnabled(false);
        userRepository.save(user);

        auditLogService.logAction(
                "DISABLE_USER",
                "User",
                user.getId(),
                user.getId(),
                "User account disabled");

        return new ApiResponseDto(
                true,
                "User disabled successfully");
    }

    @Override
    public ApiResponseDto lockUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setAccountNonLocked(false);
        userRepository.save(user);

        auditLogService.logAction(
                "LOCK_USER",
                "User",
                user.getId(),
                user.getId(),
                "User account locked");

        return new ApiResponseDto(
                true,
                "User locked successfully");
    }

    
    @Override
    public ApiResponseDto unlockUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setAccountNonLocked(true);
        user.setFailedAttempts(0);
        userRepository.save(user);

        auditLogService.logAction(
                "UNLOCK_USER",
                "User",
                user.getId(),
                user.getId(),
                "User account unlocked");

        return new ApiResponseDto(
                true,
                "User unlocked successfully");
    }


	@Override
	public AdminDashboardResponseDto getDashboard() {
		// TODO Auto-generated method stub
		 AdminDashboardResponseDto dashboard =
		            new AdminDashboardResponseDto();

		    dashboard.setTotalUsers(userRepository.count());

		    dashboard.setEnabledUsers(
		            userRepository.countByEnabledTrue());

		    dashboard.setDisabledUsers(
		            userRepository
		            .countByEnabledFalse());

		    dashboard.setLockedUsers(
		            userRepository
		            .countByAccountNonLockedFalse());

		    dashboard.setTotalRoles(
		            roleRepository.count());

		    dashboard.setTotalPermissions(
		            permissionRepository.count());

		    dashboard.setActiveSessions(
		            userSessionRepository
		            .countByActiveTrue());

		    return dashboard;
	}


	@Override
	public ApiResponseDto resetFailedAttempts(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                    		"User not found"));

	    user.setFailedAttempts(0);

	    userRepository.save(user);

	    auditLogService.logAction(
	            "RESET_FAILED_ATTEMPTS",
	            "User",
	            user.getId(),
	            user.getId(),
	            "Failed login attempts reset");

	    return new ApiResponseDto(
	            true,
	            "Failed attempts reset successfully");
	}


	@Override
	public ApiResponseDto deleteUser(Long id) {
		// TODO Auto-generated method stub
		 User user = userRepository.findById(id)
		            .orElseThrow(() ->
		                    new ResourceNotFoundException(
		                    		"User not found"));

		    auditLogService.logAction(
		            "DELETE_USER",
		            "User",
		            user.getId(),
		            user.getId(),
		            "User deleted by administrator");

		    userRepository.delete(user);

		    return new ApiResponseDto(
		            true,
		            "User deleted successfully");
	}

}
