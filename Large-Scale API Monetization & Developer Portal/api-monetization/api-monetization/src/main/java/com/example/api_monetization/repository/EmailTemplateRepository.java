package com.example.api_monetization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.EmailTemplate;

@Repository
public interface EmailTemplateRepository
        extends JpaRepository<EmailTemplate, Long> {

    Optional<EmailTemplate> findByTemplateName(String templateName);

}