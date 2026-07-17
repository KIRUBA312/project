package com.example.api_monetization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.ConsumerApplication;
@Repository
public interface ConsumerApplicationRepository extends JpaRepository<ConsumerApplication, Long> {

    List<ConsumerApplication> findByDeveloperId(Long developerId);

}