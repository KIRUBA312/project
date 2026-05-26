package com.example.taskmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanagement.dto.TaskCommentRequestDto;
import com.example.taskmanagement.dto.TaskCommentResponseDto;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.TaskComment;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.exception.ResourceNotFoundException;
import com.example.taskmanagement.repository.TaskCommentRepository;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.repository.UserRepository;
import com.example.taskmanagement.service.TaskCommentService;
import com.example.taskmanagement.util.AppConstants;

@Service
public class TaskCommentServiceImpl implements TaskCommentService{
	
	@Autowired
	private TaskCommentRepository commentRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public TaskCommentResponseDto addComment(
			Long taskId, TaskCommentRequestDto dto) {
		// TODO Auto-generated method stub
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException(
						AppConstants.TASK_NOT_FOUND));
		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER_NOT_FOUND));
		
		TaskComment comment = new TaskComment();
		comment.setTask(task);
		comment.setUser(user);
		comment.setCommentText(dto.getCommentText());
		comment.setCreatedAt(LocalDateTime.now());
		
		return maptoResponse(commentRepository.save(comment));
	}
	@Override
	public List<TaskCommentResponseDto> getCommentsByTask(Long taskId) {
		// TODO Auto-generated method stub
		
		return commentRepository.findByTaskId(taskId)
				.stream().map(this::maptoResponse)
				.collect(Collectors.toList());
	}
	private TaskCommentResponseDto maptoResponse(TaskComment comment) {
		
		TaskCommentResponseDto dto = new TaskCommentResponseDto();
		
		dto.setId(comment.getId());
		dto.setTaskTitle(comment.getTask().getTitle());
		dto.setUserName(comment.getUser().getName());
		dto.setCommentText(comment.getCommentText());
		dto.setCreatedAt(comment.getCreatedAt());
		
		return dto;
	}

}
