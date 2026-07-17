package com.example.api_monetization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.Api;
import com.example.api_monetization.enums.ApiLifecycleStatus;
@Repository
public interface ApiRepository extends JpaRepository<Api, Long> {

    List<Api> findByLifecycleStatus(ApiLifecycleStatus status);

    List<Api> findByCategoryId(Long categoryId);

}