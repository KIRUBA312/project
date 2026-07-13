package com.example.enterprise_iam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enterprise_iam.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	
	Boolean existsByEmail(String email);
	
	Long countByEnabledTrue();
	Long countByEnabledFalse();
	Long countByAccountNonLockedFalse();
}
