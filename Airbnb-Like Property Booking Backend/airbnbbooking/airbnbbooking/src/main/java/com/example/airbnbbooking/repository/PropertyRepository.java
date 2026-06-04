package com.example.airbnbbooking.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.airbnbbooking.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{
	
	List<Property> findByLocationContainingIgnoreCase(String location);
	
	List<Property> findByPricePerNightBetween(
			BigDecimal minPrice,
			BigDecimal maxPrice);
	
	List<Property> findByHostId(Long hostId);

}
