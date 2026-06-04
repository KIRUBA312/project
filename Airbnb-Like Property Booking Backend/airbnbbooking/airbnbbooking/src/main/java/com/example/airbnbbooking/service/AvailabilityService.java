package com.example.airbnbbooking.service;

import java.util.List;


import com.example.airbnbbooking.dto.AvailabilityRequestDto;

public interface AvailabilityService {

	AvailabilityRequestDto createAvailability(AvailabilityRequestDto dto);

	List<AvailabilityRequestDto> getAllAvailability();

	AvailabilityRequestDto updateAvailability(Long id, AvailabilityRequestDto dto);

	String deleteAvailability(Long id);

	List<AvailabilityRequestDto> getAvailabilityByProperty(Long propertyId);

	AvailabilityRequestDto getAvailabilityById(Long id);

}
