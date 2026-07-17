package com.example.api_monetization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_monetization.dto.admin.ApprovePublishRequest;
import com.example.api_monetization.dto.admin.EmailTemplateRequest;
import com.example.api_monetization.dto.admin.EmailTemplateResponse;
import com.example.api_monetization.dto.admin.RejectPublishRequest;
import com.example.api_monetization.dto.admin.SystemSettingRequest;
import com.example.api_monetization.dto.admin.SystemSettingResponse;
import com.example.api_monetization.dto.api.ApiPublishResponse;
import com.example.api_monetization.service.AdminService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/publish-requests")
	public ResponseEntity<List<ApiPublishResponse>>
	pendingRequests() {

	    return ResponseEntity.ok(
	            adminService.getPendingPublishRequests());
	}
	
	@PutMapping("/publish-requests/{requestId}/approve")
	public ResponseEntity<ApiPublishResponse>
	approvePublishRequest(

	@PathVariable Long requestId,

	@RequestBody ApprovePublishRequest request){

	    return ResponseEntity.ok(

	            adminService.approvePublishRequest(
	                    requestId,
	                    request));
	}
	
	@PutMapping("/publish-requests/{requestId}/reject")
	public ResponseEntity<ApiPublishResponse>
	rejectPublishRequest(

	@PathVariable Long requestId,

	@RequestBody RejectPublishRequest request){

	    return ResponseEntity.ok(

	            adminService.rejectPublishRequest(
	                    requestId,
	                    request));
	}
	
	 @GetMapping("/settings")
	    public ResponseEntity<List<SystemSettingResponse>> getAllSettings() {

	        return ResponseEntity.ok(
	                adminService.getAllSettings());
	    }

	    @PutMapping("/settings/{id}")
	    public ResponseEntity<SystemSettingResponse> updateSetting(
	            @PathVariable Long id,
	            @Valid @RequestBody SystemSettingRequest request) {

	        return ResponseEntity.ok(
	                adminService.updateSetting(id, request));
	    }


	    @GetMapping("/email-templates")
	    public ResponseEntity<List<EmailTemplateResponse>> getAllTemplates() {

	        return ResponseEntity.ok(
	                adminService.getAllTemplates());
	    }

	    @GetMapping("/email-templates/{id}")
	    public ResponseEntity<EmailTemplateResponse> getTemplate(
	            @PathVariable Long id) {

	        return ResponseEntity.ok(
	                adminService.getTemplate(id));
	    }

	    @PutMapping("/email-templates/{id}")
	    public ResponseEntity<EmailTemplateResponse> updateTemplate(
	            @PathVariable Long id,
	            @Valid @RequestBody EmailTemplateRequest request) {

	        return ResponseEntity.ok(
	                adminService.updateTemplate(id, request));
	    }
}
