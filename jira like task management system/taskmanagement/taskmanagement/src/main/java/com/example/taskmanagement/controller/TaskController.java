package com.example.taskmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanagement.dto.ApiResponseDto;
import com.example.taskmanagement.dto.TaskRequestDto;
import com.example.taskmanagement.dto.TaskResponseDto;
import com.example.taskmanagement.enums.TaskStatus;
import com.example.taskmanagement.service.TaskService;
import com.example.taskmanagement.util.AppConstants;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping
	public ResponseEntity<TaskResponseDto> createTask(
			@RequestBody TaskRequestDto dto){
		return new ResponseEntity<>(taskService.createTask(dto),
				HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<TaskResponseDto>> getAllTasks(){
		return ResponseEntity.ok(taskService.getAllTasks());
	}
	@GetMapping("/{id}")
	public ResponseEntity<TaskResponseDto> getTaskById(
			@PathVariable Long id){
		return ResponseEntity.ok(taskService.getTaskById(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<TaskResponseDto> updateTask(
			@PathVariable Long id,@RequestBody TaskRequestDto dto){
		return ResponseEntity.ok(taskService.updateTask(id,dto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseDto> deleteTask(
			@PathVariable Long id){
		
		taskService.deleteTask(id);
		return ResponseEntity.ok(new ApiResponseDto(
				AppConstants.TASK_DELETED,200));
		
	}
	
	@GetMapping("/project/{projectId}")
	public ResponseEntity<List<TaskResponseDto>> getTasksByProject(
			@PathVariable Long projectId){
		return ResponseEntity.ok(taskService.getTasksByProject(projectId));
	}
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<TaskResponseDto>> getTaskByUser(
			@PathVariable Long userId){
		
		return ResponseEntity.ok(taskService.getTasksByUser(userId));
	}
	@GetMapping("/status/{status}")
	public ResponseEntity<List<TaskResponseDto>> getTaskByStatus(
			@PathVariable TaskStatus status){
		
		return ResponseEntity.ok(taskService.getTasksByStatus(status));
	}
	@GetMapping("/search")
	public ResponseEntity<Page<TaskResponseDto>> searchTasks(
			@RequestParam String keyword,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5")int size){
		
		return ResponseEntity.ok(taskService.searchTasks(
				keyword,page,size));
		
	}
	
	
	
}
