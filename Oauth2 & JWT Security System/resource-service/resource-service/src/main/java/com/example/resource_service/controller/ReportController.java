package com.example.resource_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
	
	@GetMapping("/tenant")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	public String tenantReport() {
		return "Tenant Report";
	}
	@GetMapping("/resource")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
	public String resourceReport() {
		return "Resource Report";
	}

}
