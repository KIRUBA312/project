package com.example.enterprise_iam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enterprise_iam.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(String name);
	Boolean existsByName(String name);
}
