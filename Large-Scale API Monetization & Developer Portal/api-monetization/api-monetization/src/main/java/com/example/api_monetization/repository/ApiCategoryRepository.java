package com.example.api_monetization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.ApiCategory;
@Repository
public interface ApiCategoryRepository extends JpaRepository<ApiCategory, Long> {

    Optional<ApiCategory> findByCategoryName(String categoryName);
    boolean existsByCategoryNameIgnoreCase(String categoryName);

}