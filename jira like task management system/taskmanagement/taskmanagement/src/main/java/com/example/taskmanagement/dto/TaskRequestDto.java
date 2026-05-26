package com.example.taskmanagement.dto;

import java.time.LocalDate;

import com.example.taskmanagement.enums.TaskPriority;
import com.example.taskmanagement.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskRequestDto {

	@NotBlank(message = "Task title is required")
	private String title;
	
	private String description;
	
	@NotNull(message = "Task status is required")
	private TaskStatus status;
	
	@NotNull(message = "Task priority is required")
	private TaskPriority priority;
	
	@NotNull(message = "Assigned user id is required")
	private Long assignedUserId;
	
	@NotNull(message = "Project id is required")
	private Long projectId;
	
	@NotNull(message = "Due date is required")
	private LocalDate dueDate;
	
	public TaskRequestDto() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public Long getAssignedUserId() {
		return assignedUserId;
	}

	public void setAssignedUserId(Long assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	
}
