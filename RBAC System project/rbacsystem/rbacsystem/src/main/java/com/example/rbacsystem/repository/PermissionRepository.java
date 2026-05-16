package com.example.rbacsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rbacsystem.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
