package com.example.taskmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanagement.dto.DashboardResponseDto;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.enums.TaskStatus;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService{

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public DashboardResponseDto getDashboardMetrics() {
		// TODO Auto-generated method stub
		List<Task> tasks = taskRepository.findAll();
		long total = tasks.size();
		long completed = tasks.stream()
				.filter(task ->task.getStatus() 
						== TaskStatus.DONE)
				.count();
		long bloacked = tasks.stream()
				.filter(task ->
				task.getStatus() == TaskStatus.BLOCKED)
				.count();
		long pending = tasks.stream()
				.filter(task ->
				task.getStatus() == TaskStatus.OPEN
				|| task.getStatus() == TaskStatus.IN_PROGRESS)
				.count();
		
		DashboardResponseDto dto = new DashboardResponseDto();
		dto.setTotalTasks(total);
		dto.setCompletedTasks(completed);
		dto.setPendingTasks(pending);
		dto.setBlockedTasks(bloacked);
		
		return dto;
		
	}
	
	
	
}
