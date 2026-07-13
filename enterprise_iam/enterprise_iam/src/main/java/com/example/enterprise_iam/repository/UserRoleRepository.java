package com.example.enterprise_iam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enterprise_iam.entity.Role;
import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

	List<UserRole> findByUser(User user);

	Optional<UserRole> findByUserAndRole(User user, Role role);
}
