package com.example.cdc_synchronization_engine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cdc_synchronization_engine.entity.User;
import com.example.cdc_synchronization_engine.entity.UserRole;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUser(User user);

    void deleteByUser(User user);

    Optional<UserRole> findByUserIdAndRoleId(
            Long userId,
            Long roleId);

    boolean existsByUserIdAndRoleId(
            Long userId,
            Long roleId);
	List<UserRole> findByUserId(Long id);
}