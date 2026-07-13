package com.example.enterprise_iam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enterprise_iam.entity.Permission;
import com.example.enterprise_iam.entity.Role;
import com.example.enterprise_iam.entity.RolePermission;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long>{

	List<RolePermission> findByRole(Role role);

	Optional<RolePermission> findByRoleAndPermission(Role role, Permission permission);
}
