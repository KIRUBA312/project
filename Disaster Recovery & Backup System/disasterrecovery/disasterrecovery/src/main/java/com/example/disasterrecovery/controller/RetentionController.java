package com.example.disasterrecovery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.disasterrecovery.service.RetentionPolicyService;

@RestController
@RequestMapping("/api/retention")
public class RetentionController {

	@Autowired
	private RetentionPolicyService retentionPolicyService;
	
	@GetMapping("/expired-count")
	public ResponseEntity<Long> getExpiredBackupCount(){
		return ResponseEntity.ok(retentionPolicyService
				.getExpiredBackupCount());
	}
	@DeleteMapping("/cleanup")
	public ResponseEntity<String> cleanupExpiredBackups(){
		return ResponseEntity.ok(retentionPolicyService
				.executeRetentionPolicy());
	}
	
	
}
