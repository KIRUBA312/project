package com.example.auth_server.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_server.entity.RefreshToken;
import com.example.auth_server.entity.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

	Optional<RefreshToken> findByToken(String token);
	Optional<RefreshToken> findByUser(User user);
	void deleteByUser(User user);
	void deleteByExpiryDateBefore(LocalDateTime dateTime);
	
}
