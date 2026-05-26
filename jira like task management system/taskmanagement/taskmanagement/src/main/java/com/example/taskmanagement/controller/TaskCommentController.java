package com.example.taskmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanagement.dto.TaskCommentRequestDto;
import com.example.taskmanagement.dto.TaskCommentResponseDto;
import com.example.taskmanagement.service.TaskCommentService;

@RestController
@RequestMapping("/api/tasks")
public class TaskCommentController {
	
	@Autowired
	private TaskCommentService taskCommentService;
	
	@PostMapping("/{taskId}/comments")
	public ResponseEntity<TaskCommentResponseDto> addComment(
			@PathVariable Long taskId,
			@RequestBody TaskCommentRequestDto dto){
		
		return new ResponseEntity<>(
				taskCommentService.addComment(
						taskId,dto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{taskId}/comments")
	public ResponseEntity<List<TaskCommentResponseDto>> getCommentsById(
			@PathVariable Long taskId){
		
		return ResponseEntity.ok(taskCommentService
				.getCommentsByTask(taskId));
	}

}
