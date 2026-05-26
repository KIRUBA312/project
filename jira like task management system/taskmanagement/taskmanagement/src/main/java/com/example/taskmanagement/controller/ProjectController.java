package com.example.taskmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanagement.dto.ApiResponseDto;
import com.example.taskmanagement.dto.ProjectRequestDto;
import com.example.taskmanagement.dto.ProjectResponseDto;
import com.example.taskmanagement.service.ProjectService;
import com.example.taskmanagement.util.AppConstants;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping
	public ResponseEntity<ProjectResponseDto> createProject(
			@RequestBody ProjectRequestDto dto){
		
		return new ResponseEntity<>(
				projectService.createProject(dto),
				HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ProjectResponseDto>> getAllProjects(){
		
		return ResponseEntity.ok(projectService.getAllProjects());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjectResponseDto> getProjectById(
			@PathVariable Long id){
		return ResponseEntity.ok(projectService.getProjectById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProjectResponseDto> updateProject(
			@PathVariable Long id,@RequestBody ProjectRequestDto dto){
		return ResponseEntity.ok(
				projectService.updateProject(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseDto> deleteProject(
			@PathVariable Long id){
		
		projectService.deleteProject(id);
		
		return ResponseEntity.ok(new ApiResponseDto(
				AppConstants.PROJECT_DELETED,200));
	}

}
