package com.example.apigateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apigateway.entity.RequestLog;
import com.example.apigateway.service.LogService;
import com.example.apigateway.util.ApiKeyValidator;

@RestController
@RequestMapping("/api/logs")
public class LogController {
	
	@Autowired
	private LogService logService;
	
	@GetMapping
	public ResponseEntity<List<RequestLog>> getAllLogs(
			@RequestHeader(value = "X-API-KEY")String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		
		return ResponseEntity.ok(logService.getAllLogs());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestLog> getLogById(
			@PathVariable Long id,
			@RequestHeader(value = "X-API-KEY",required = false)String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		
		return ResponseEntity.ok(logService.getLogById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLog(
			@PathVariable Long id,
			@RequestHeader(value = "X-API-KEY",required = false)
			String apiKey){
		
		ApiKeyValidator.validate(apiKey);
		
		logService.deleteLog(id);
		return ResponseEntity.ok("Log Deleted Successfully");
		
	}
	
	

}
