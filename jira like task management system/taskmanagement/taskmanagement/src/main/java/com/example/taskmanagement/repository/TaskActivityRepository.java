package com.example.taskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanagement.entity.TaskActivity;

@Repository
public interface TaskActivityRepository extends JpaRepository<TaskActivity, Long>{

	List<TaskActivity> findByTaskId(Long taskId);
}
