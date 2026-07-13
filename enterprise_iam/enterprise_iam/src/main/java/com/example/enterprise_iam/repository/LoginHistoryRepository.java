package com.example.enterprise_iam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enterprise_iam.entity.LoginHistory;
import com.example.enterprise_iam.entity.User;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long>{

	List<LoginHistory> findByUser(User user);
}
