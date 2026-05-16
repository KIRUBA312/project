package com.example.rbacsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rbacsystem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
