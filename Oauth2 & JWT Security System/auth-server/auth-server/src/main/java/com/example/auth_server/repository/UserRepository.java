package com.example.auth_server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_server.entity.Tenant;
import com.example.auth_server.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	Optional<User> findByUsernameAndTenant(String username,Tenant tenant);
	List<User> findByTenant(Tenant tenant);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
