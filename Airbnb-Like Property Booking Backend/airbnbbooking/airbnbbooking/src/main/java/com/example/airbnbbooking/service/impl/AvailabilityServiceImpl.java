package com.example.airbnbbooking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airbnbbooking.dto.AvailabilityRequestDto;
import com.example.airbnbbooking.entity.Property;
import com.example.airbnbbooking.entity.PropertyAvailability;
import com.example.airbnbbooking.exception.ResourceNotFoundException;
import com.example.airbnbbooking.repository.PropertyAvailabilityRepository;
import com.example.airbnbbooking.repository.PropertyRepository;
import com.example.airbnbbooking.service.AvailabilityService;

@Service
public class AvailabilityServiceImpl implements AvailabilityService{

	@Autowired
	private PropertyAvailabilityRepository availabilityRepository;
	
	@Autowired
	private PropertyRepository propertyRepository;

	@Override
	public AvailabilityRequestDto createAvailability(AvailabilityRequestDto dto) {
		// TODO Auto-generated method stub
		Property property = propertyRepository.findById(dto.getPropertyId())
				.orElseThrow(() -> new ResourceNotFoundException("Property not found"));
		PropertyAvailability availability = new PropertyAvailability();
		availability.setProperty(property);
		availability.setAvailableFrom(dto.getAvailableFrom());
		availability.setAvailableTo(dto.getAvailableTo());
		
		availabilityRepository.save(availability);
		return dto;
	}

	@Override
	public List<AvailabilityRequestDto> getAllAvailability() {
		// TODO Auto-generated method stub
		return availabilityRepository.findAll().stream()
				.map(this::maptodto)
				.collect(Collectors.toList());
	}
	
	@Override
	public AvailabilityRequestDto getAvailabilityById(Long id) {
		// TODO Auto-generated method stub
		PropertyAvailability availability = availabilityRepository
				.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Availability not found"));
		return maptodto(availability);
	}

	@Override
	public AvailabilityRequestDto updateAvailability(Long id, 
			AvailabilityRequestDto dto) {
		// TODO Auto-generated method stub
		PropertyAvailability availability = availabilityRepository
				.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Availability not found"));
		Property property = propertyRepository.findById(dto.getPropertyId())
				.orElseThrow(() ->
				new ResourceNotFoundException("Property not found"));
		availability.setProperty(property);
		availability.setAvailableFrom(dto.getAvailableFrom());
		availability.setAvailableTo(dto.getAvailableTo());
		
		availabilityRepository.save(availability);
		return maptodto(availability);
	}

	@Override
	public String deleteAvailability(Long id) {
		// TODO Auto-generated method stub
		PropertyAvailability availability = availabilityRepository
				.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Availability not found"));
		availabilityRepository.delete(availability);
		return "Availability deleted successfully";
	}

	@Override
	public List<AvailabilityRequestDto> getAvailabilityByProperty(Long propertyId) {
		// TODO Auto-generated method stub
		return availabilityRepository.findByPropertyId(propertyId)
				.stream().map(this::maptodto)
				.collect(Collectors.toList());
	}
	
	private AvailabilityRequestDto maptodto(PropertyAvailability availability) {
		AvailabilityRequestDto dto = new AvailabilityRequestDto();
		dto.setPropertyId(availability.getId());
		dto.setAvailableFrom(availability.getAvailableFrom());
		dto.setAvailableTo(availability.getAvailableTo());
		
		return dto;
		
	}

	
	
	
	
}
