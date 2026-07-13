package com.example.enterprise_iam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.entity.UserSession;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long>{

	List<UserSession> findByUser(User user);
	Optional<UserSession> findByJwtToken(String jwtToken);
	
	Long countByActiveTrue();
}
