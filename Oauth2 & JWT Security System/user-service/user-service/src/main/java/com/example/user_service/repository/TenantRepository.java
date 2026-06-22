package com.example.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user_service.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long>{
	
	Optional<Tenant> findByTenantCode(String tenantCode);

}
