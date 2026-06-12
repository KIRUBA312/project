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

import com.example.disasterrecovery.dto.RestoreRequestDto;
import com.example.disasterrecovery.dto.RestoreResponseDto;
import com.example.disasterrecovery.dto.RestoreStatusDto;
import com.example.disasterrecovery.service.RestoreService;

@RestController
@RequestMapping("/api/restore")
public class RestoreController {

	@Autowired
	private RestoreService restoreService;
	
	@PostMapping
	public ResponseEntity<RestoreResponseDto> restoreBackup(
			@RequestBody RestoreRequestDto dto){
		return ResponseEntity.ok(
				restoreService.restoreBackup(dto));
	}
	@GetMapping("/status/{id}")
	public ResponseEntity<RestoreStatusDto> getRestoreStatus(
			@PathVariable Long id){
		return ResponseEntity.ok(restoreService.getRestoreStatus(id));
	}
	@GetMapping
	public ResponseEntity<List<RestoreResponseDto>>getAllRestoreLogs(){
		return ResponseEntity.ok(restoreService.getAllRestoreLogs());
	}
	@GetMapping("/{id}")
	public ResponseEntity<RestoreResponseDto> getRestoreById(
			@PathVariable Long id){
		return ResponseEntity.ok(restoreService.getRestoreById(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<RestoreResponseDto> updateRestore(
			@PathVariable Long id,
			@RequestBody RestoreRequestDto dto){
		return ResponseEntity.ok(restoreService.updateRestore(id,dto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRestoreLog(
			@PathVariable Long id){
		return ResponseEntity.ok(restoreService.deleteRestoreLog(id));
	}
	
}
