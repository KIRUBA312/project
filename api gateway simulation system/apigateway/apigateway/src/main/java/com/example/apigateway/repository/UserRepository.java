package com.example.apigateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apigateway.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
