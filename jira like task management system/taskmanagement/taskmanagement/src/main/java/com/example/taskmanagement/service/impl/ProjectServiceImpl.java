package com.example.taskmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanagement.dto.ProjectRequestDto;
import com.example.taskmanagement.dto.ProjectResponseDto;
import com.example.taskmanagement.entity.Project;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.exception.ResourceNotFoundException;
import com.example.taskmanagement.repository.ProjectRepository;
import com.example.taskmanagement.repository.UserRepository;
import com.example.taskmanagement.service.ProjectService;
import com.example.taskmanagement.util.AppConstants;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public ProjectResponseDto createProject(ProjectRequestDto dto) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(dto.getCreatedBy())
				.orElseThrow(() -> new ResourceNotFoundException(
						AppConstants.USER_NOT_FOUND));
		Project project = new Project();
		
		project.setName(dto.getName());
		project.setDescription(dto.getDescription());
		project.setCreatedBy(user);
		project.setCreatedAt(LocalDateTime.now());
		
		return maptoResponse(projectRepository.save(project));
	}

	@Override
	public List<ProjectResponseDto> getAllProjects() {
		// TODO Auto-generated method stub
		return projectRepository.findAll().stream()
				.map(this::maptoResponse)
				.collect(Collectors.toList());
	}

	@Override
	public ProjectResponseDto getProjectById(Long id) {
		// TODO Auto-generated method stub
		Project project = projectRepository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException(
						AppConstants.PROJECT_NOT_FOUND));
		return maptoResponse(project);
	}

	@Override
	public ProjectResponseDto updateProject(Long id, ProjectRequestDto dto) {
		// TODO Auto-generated method stub
		Project project = projectRepository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException(
						AppConstants.PROJECT_NOT_FOUND));
		project.setName(dto.getName());
		project.setDescription(dto.getDescription());
		return maptoResponse(projectRepository.save(project));
	}

	@Override
	public void deleteProject(Long id) {
		// TODO Auto-generated method stub
		Project project = projectRepository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException(
						AppConstants.PROJECT_NOT_FOUND));
		projectRepository.delete(project);
		
	}
	
	private ProjectResponseDto maptoResponse(Project project) {
		ProjectResponseDto dto = new ProjectResponseDto();
		
		dto.setId(project.getId());
		dto.setName(project.getName());
		dto.setDescription(project.getDescription());
		dto.setCreatedBy(project.getCreatedBy().getName());
		dto.setCreatedAt(project.getCreatedAt());
		
		return dto;
	}

}
