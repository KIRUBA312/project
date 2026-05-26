package com.example.taskmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.taskmanagement.dto.TaskRequestDto;
import com.example.taskmanagement.dto.TaskResponseDto;
import com.example.taskmanagement.entity.Project;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.enums.TaskStatus;
import com.example.taskmanagement.exception.ResourceNotFoundException;
import com.example.taskmanagement.repository.ProjectRepository;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.repository.UserRepository;
import com.example.taskmanagement.service.TaskService;
import com.example.taskmanagement.util.AppConstants;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Override
	public TaskResponseDto createTask(TaskRequestDto dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(dto.getAssignedUserId())
				.orElseThrow(() -> new ResourceNotFoundException(
						AppConstants.USER_NOT_FOUND));
		Project project = projectRepository.findById(dto.getProjectId())
				.orElseThrow(() -> new ResourceNotFoundException(
						AppConstants.PROJECT_NOT_FOUND));
		Task task = new Task();
		
		task.setTitle(dto.getTitle());
		task.setDescription(dto.getDescription());
		task.setStatus(dto.getStatus());
		task.setPriority(dto.getPriority());
		task.setAssignedUser(user);
		task.setProject(project);
		task.setDueDate(dto.getDueDate());
		task.setCreatedAt(LocalDateTime.now());
		
		return maptoResponse(taskRepository.save(task));
	}
	@Override
	public List<TaskResponseDto> getAllTasks() {
		// TODO Auto-generated method stub
		return taskRepository.findAll().stream()
				.map(this::maptoResponse)
				.collect(Collectors.toList());
	}
	@Override
	public TaskResponseDto getTaskById(Long id) {
		// TODO Auto-generated method stub
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						AppConstants.TASK_NOT_FOUND));
		return maptoResponse(task);
	}
	@Override
	public TaskResponseDto updateTask(Long id, TaskRequestDto dto) {
		// TODO Auto-generated method stub
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						AppConstants.TASK_NOT_FOUND));

		User user = userRepository.findById(dto.getAssignedUserId())
				.orElseThrow(() -> new ResourceNotFoundException(
						AppConstants.USER_NOT_FOUND));
		Project project = projectRepository.findById(dto.getProjectId())
				.orElseThrow(() -> new ResourceNotFoundException(
						AppConstants.PROJECT_NOT_FOUND));
		
		task.setTitle(dto.getTitle());
		task.setDescription(dto.getDescription());
		task.setStatus(dto.getStatus());
		task.setPriority(dto.getPriority());
		task.setAssignedUser(user);
		task.setProject(project);
		task.setDueDate(dto.getDueDate());
		return maptoResponse(taskRepository.save(task));
	}
	@Override
	public void deleteTask(Long id) {
		// TODO Auto-generated method stub
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						AppConstants.TASK_NOT_FOUND));
		taskRepository.delete(task);
		
	}
	@Override
	public List<TaskResponseDto> getTasksByProject(Long projectId) {
		// TODO Auto-generated method stub
		return taskRepository.findByProjectId(projectId).stream()
				.map(this::maptoResponse)
				.collect(Collectors.toList());
	}
	@Override
	public List<TaskResponseDto> getTasksByUser(Long userId) {
		// TODO Auto-generated method stub
		return taskRepository.findByAssignedUserId(userId).stream()
				.map(this::maptoResponse)
				.collect(Collectors.toList());
	}
	@Override
	public List<TaskResponseDto> getTasksByStatus(TaskStatus status) {
		// TODO Auto-generated method stub
		return taskRepository.findByStatus(status).stream()
				.map(this::maptoResponse)
				.collect(Collectors.toList());
	}
	@Override
	public Page<TaskResponseDto> searchTasks(String keyword, int page, int size) {
		// TODO Auto-generated method stub
		Page<Task> tasks = taskRepository.findByTitleContainingIgnoreCase(
				keyword, PageRequest.of(page, size));
		List<TaskResponseDto> dtoList = tasks.getContent().stream()
				.map(this::maptoResponse).collect(Collectors.toList());
		return new PageImpl<>(dtoList);
	}
	
	private TaskResponseDto maptoResponse(Task task) {
		TaskResponseDto dto = new TaskResponseDto();
		dto.setId(task.getId());
		dto.setTitle(task.getTitle());
		dto.setDescription(task.getDescription());
		dto.setStatus(task.getStatus());
		dto.setPriority(task.getPriority());
		dto.setAssignedUser(task.getAssignedUser().getName());
		dto.setProjectName(task.getProject().getName());
		dto.setDueDate(task.getDueDate());
		dto.setCreatedAt(task.getCreatedAt());
		
		return dto;
		
	}
	

}
