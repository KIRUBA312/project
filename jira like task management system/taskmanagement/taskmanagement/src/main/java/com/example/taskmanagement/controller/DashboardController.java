package com.example.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanagement.dto.DashboardResponseDto;
import com.example.taskmanagement.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("/metrics")
	public ResponseEntity<DashboardResponseDto> getDashboardMetrics(){
		return ResponseEntity.ok(
				dashboardService.getDashboardMetrics());
	}
	
}
