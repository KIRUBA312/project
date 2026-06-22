package com.example.resource_service.controller;

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

import com.example.resource_service.dto.ResourceRequestDto;
import com.example.resource_service.dto.ResourceResponseDto;
import com.example.resource_service.service.ResourceService;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
	
	@PostMapping
	public ResponseEntity<ResourceResponseDto> createResource(
			@RequestBody ResourceRequestDto dto){
		return ResponseEntity.ok(resourceService
				.createResource(dto));
	}
	@GetMapping
	public ResponseEntity<List<ResourceResponseDto>> getAllResources(){
		return ResponseEntity.ok(resourceService.getAllResources());
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResourceResponseDto> getById(
			@PathVariable Long id){
		return ResponseEntity.ok(resourceService.getResourceById(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<ResourceResponseDto> updateResource(
			@PathVariable Long id,@RequestBody ResourceRequestDto dto){
		return ResponseEntity.ok(resourceService.updateResource(id,dto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteResource(@PathVariable Long id){
		return ResponseEntity.ok(resourceService.deleteResource(id));
	}
	@GetMapping("/tenant/{tenantId}")
	public ResponseEntity<List<ResourceResponseDto>> getResourcesByTenant(
			@PathVariable Long tenantId){
		return ResponseEntity.ok(resourceService.getResourcesByTenant(tenantId));
	}
	
	
}
