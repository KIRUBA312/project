package com.example.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanagement.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
