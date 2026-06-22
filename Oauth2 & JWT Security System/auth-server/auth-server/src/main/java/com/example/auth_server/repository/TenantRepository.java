package com.example.auth_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_server.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long>{

	Optional<Tenant> findByTenantCode(String tenantCode);
	boolean existsByTenantCode(String tenantCode);
}
