package com.example.taskmanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.taskmanagement.dto.TaskRequestDto;
import com.example.taskmanagement.dto.TaskResponseDto;
import com.example.taskmanagement.enums.TaskStatus;

public interface TaskService {

	TaskResponseDto createTask(TaskRequestDto dto);

	List<TaskResponseDto> getAllTasks();

	TaskResponseDto getTaskById(Long id);

	TaskResponseDto updateTask(Long id, TaskRequestDto dto);

	void deleteTask(Long id);

	List<TaskResponseDto> getTasksByProject(Long projectId);

	List<TaskResponseDto> getTasksByUser(Long userId);

	List<TaskResponseDto> getTasksByStatus(TaskStatus status);

	Page<TaskResponseDto> searchTasks(String keyword, int page, int size);

}
