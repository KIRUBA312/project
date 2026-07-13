package com.example.enterprise_iam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enterprise_iam.entity.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

	Optional<VerificationToken> findByToken(String token);
}
