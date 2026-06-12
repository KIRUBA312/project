package com.example.disasterrecovery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.disasterrecovery.dto.BackupRequestDto;
import com.example.disasterrecovery.dto.BackupResponseDto;
import com.example.disasterrecovery.dto.BackupStatusDto;
import com.example.disasterrecovery.service.BackupService;

@RestController
@RequestMapping("/api/backup")
public class BackupController {

	@Autowired
	private BackupService backupService;
	
	@PostMapping("/start")
	public ResponseEntity<BackupResponseDto> startBackup(
			@RequestBody BackupRequestDto dto){
		
		return ResponseEntity.ok(backupService.startBackup(dto));
	}
	@GetMapping("/status/{id}")
	public ResponseEntity<BackupStatusDto> getBackupStatus(
			@PathVariable Long id){
		return ResponseEntity.ok(backupService.getBackupStatus(id));
	}
	@GetMapping
	public ResponseEntity<List<BackupResponseDto>> getAllBackups(){
		return ResponseEntity.ok(backupService.getAllBackups());
	}
	@GetMapping("/{id}")
	public  ResponseEntity<BackupResponseDto> getBackupById(
			@PathVariable Long id){
		return ResponseEntity.ok(backupService.getBackupById(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<BackupResponseDto> updateBackup(
			@PathVariable Long id,@RequestBody BackupRequestDto dto){
		return ResponseEntity.ok(backupService
				.updateBackup(id, dto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBackup(@PathVariable Long id){
		return ResponseEntity.ok(backupService.deleteBackup(id));
	}
	
	
}
