package com.example.airbnbbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.airbnbbooking.entity.PropertyAvailability;

@Repository
public interface PropertyAvailabilityRepository 
extends JpaRepository<PropertyAvailability, Long>{

	List<PropertyAvailability> findByPropertyId(Long propertyId);
	
}
