package com.example.airbnbbooking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airbnbbooking.dto.PropertyRequestDto;
import com.example.airbnbbooking.dto.PropertyResponseDto;
import com.example.airbnbbooking.entity.Property;
import com.example.airbnbbooking.entity.User;
import com.example.airbnbbooking.exception.ResourceNotFoundException;
import com.example.airbnbbooking.repository.PropertyRepository;
import com.example.airbnbbooking.repository.UserRepository;
import com.example.airbnbbooking.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService{

	@Autowired
	private PropertyRepository propertyRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public PropertyResponseDto createProperty(PropertyRequestDto dto) {
		// TODO Auto-generated method stub
		User host = userRepository.findById(dto.getHostId())
				.orElseThrow(() -> new ResourceNotFoundException("Host not found"));
		
		Property property = new Property();
		property.setTitle(dto.getTitle());
		property.setDescription(dto.getDescription());
		property.setLocation(dto.getLocation());
		property.setPricePerNight(dto.getPricePerNight());
		property.setHost(host);
		
		property = propertyRepository.save(property);
		
		return maptoresponse(property);
	}
	@Override
	public List<PropertyResponseDto> getAllProperties() {
		// TODO Auto-generated method stub
		return propertyRepository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	@Override
	public  PropertyResponseDto getProprtyById(Long id) {
		// TODO Auto-generated method stub
		Property property = propertyRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Property not found"));
		return maptoresponse(property);
	}
	@Override
	public  PropertyResponseDto updateProperty(Long id, 
			PropertyRequestDto dto) {
		// TODO Auto-generated method stub
		Property property = propertyRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Property not found"));
		User host = userRepository.findById(dto.getHostId())
				.orElseThrow(() -> new ResourceNotFoundException("Host not found"));
		
		property.setTitle(dto.getTitle());
		property.setDescription(dto.getDescription());
		property.setLocation(dto.getLocation());
		property.setPricePerNight(dto.getPricePerNight());
		property.setHost(host);
		
		property = propertyRepository.save(property);
		
		return maptoresponse(property);
	}
	@Override
	public @Nullable String deleteProperty(Long id) {
		// TODO Auto-generated method stub
		Property property = propertyRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Property not found"));
		propertyRepository.delete(property);
		return "Property deleted successfully";
	}
	@Override
	public List<PropertyResponseDto> getPropertiesByLocation(String location) {
		// TODO Auto-generated method stub
		return propertyRepository.findByLocationContainingIgnoreCase(location)
				.stream().map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	@Override
	public List<PropertyResponseDto> getPropertiesByHostId(
			Long hostId) {
		// TODO Auto-generated method stub
		return propertyRepository.findByHostId(hostId)
				.stream().map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	
	private PropertyResponseDto maptoresponse(Property property) {
		PropertyResponseDto dto = new PropertyResponseDto();
		dto.setId(property.getId());
		dto.setTitle(property.getTitle());
		dto.setDescription(property.getDescription());
		dto.setLocation(property.getLocation());
		dto.setPricePerNight(property.getPricePerNight());
		dto.setHostId(property.getHost().getId());
		dto.setHostName(property.getHost().getName());
		return dto;
	}
	
}
