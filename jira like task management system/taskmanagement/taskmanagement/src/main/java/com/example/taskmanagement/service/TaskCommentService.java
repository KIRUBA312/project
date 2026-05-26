package com.example.taskmanagement.service;

import java.util.List;

import com.example.taskmanagement.dto.TaskCommentRequestDto;
import com.example.taskmanagement.dto.TaskCommentResponseDto;

public interface TaskCommentService {

	TaskCommentResponseDto addComment(Long taskId, 
			TaskCommentRequestDto dto);

	List<TaskCommentResponseDto> getCommentsByTask(Long taskId);

}
