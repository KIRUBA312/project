package com.example.enterprise_iam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enterprise_iam.dto.response.AdminDashboardResponseDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.UserResponseDto;
import com.example.enterprise_iam.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/dashboard")
	public ResponseEntity<AdminDashboardResponseDto> getDashboard(){
		return ResponseEntity.ok(adminService.getDashboard());
	}
	@GetMapping("/users")
	public ResponseEntity<List<UserResponseDto>> getAllUsers(){
		return ResponseEntity.ok(adminService.getAllUsers());
	}
	@GetMapping("/users/{id}")
	public ResponseEntity<UserResponseDto> getUserById(
			@PathVariable Long id){
		return ResponseEntity.ok(adminService.getUserById(id));
	}
	@PutMapping("/users/{id}/enable")
	public ResponseEntity<ApiResponseDto> enableUser(
			@PathVariable Long id){
		return ResponseEntity.ok(adminService.enableUser(id));
	}
	@PutMapping("users/{id}/disable")
	public ResponseEntity<ApiResponseDto> disableUser(
			@PathVariable Long id){
		return ResponseEntity.ok(adminService.disableUser(id));
	}
	@PutMapping("/users/{id}/lock")
	public ResponseEntity<ApiResponseDto> lockUser(@PathVariable Long id){
		return ResponseEntity.ok(adminService.lockUser(id));
	}
    @PutMapping("/users/{id}/unlock")
    public ResponseEntity<ApiResponseDto> unlockUser(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminService.unlockUser(id));
    }

    
    @PutMapping("/users/{id}/reset-attempts")
    public ResponseEntity<ApiResponseDto> resetFailedAttempts(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminService.resetFailedAttempts(id));
    }

    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponseDto> deleteUser(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminService.deleteUser(id));
    }
}
