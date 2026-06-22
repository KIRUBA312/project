package com.example.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user_service.entity.Permission;

@Repository
public interface PermissionRepository extends 
		JpaRepository<Permission, Long>{

	Optional<Permission> findByPermissionName(String permissionName);
	boolean existsByPermissionName(String permissionName);
	
}
