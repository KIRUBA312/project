package com.example.taskmanagement.service;

import java.util.List;


import com.example.taskmanagement.dto.ProjectRequestDto;
import com.example.taskmanagement.dto.ProjectResponseDto;

public interface ProjectService {

	ProjectResponseDto createProject(ProjectRequestDto dto);

	List<ProjectResponseDto> getAllProjects();

	ProjectResponseDto getProjectById(Long id);

	ProjectResponseDto updateProject(Long id, ProjectRequestDto dto);

	void deleteProject(Long id);

}
