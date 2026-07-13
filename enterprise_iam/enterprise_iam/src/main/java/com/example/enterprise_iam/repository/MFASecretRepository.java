package com.example.enterprise_iam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enterprise_iam.entity.MFASecret;
import com.example.enterprise_iam.entity.User;

@Repository
public interface MFASecretRepository extends JpaRepository<MFASecret, Long>{

	Optional<MFASecret> findByUser(User user);
}
