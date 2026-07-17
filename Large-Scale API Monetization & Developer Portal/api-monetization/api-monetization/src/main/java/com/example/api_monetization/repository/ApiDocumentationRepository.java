package com.example.api_monetization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.ApiDocumentation;

@Repository
public interface ApiDocumentationRepository
        extends JpaRepository<ApiDocumentation, Long> {

    Optional<ApiDocumentation> findByApiId(Long apiId);

}