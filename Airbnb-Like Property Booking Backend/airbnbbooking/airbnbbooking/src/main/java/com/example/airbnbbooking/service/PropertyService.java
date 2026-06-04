package com.example.airbnbbooking.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.airbnbbooking.dto.PropertyRequestDto;
import com.example.airbnbbooking.dto.PropertyResponseDto;

public interface PropertyService {

	PropertyResponseDto createProperty(PropertyRequestDto dto);

	List<PropertyResponseDto> getAllProperties();

	@Nullable
	PropertyResponseDto getProprtyById(Long id);

	@Nullable
	PropertyResponseDto updateProperty(Long id, PropertyRequestDto dto);

	@Nullable
	String deleteProperty(Long id);

	List<PropertyResponseDto> getPropertiesByLocation(String location);

	List<PropertyResponseDto> getPropertiesByHostId(Long hostId);

}
