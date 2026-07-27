package com.example.multiregion_resilience.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.multiregion_resilience.dto.PageResponse;
import com.example.multiregion_resilience.dto.RegionRequest;
import com.example.multiregion_resilience.dto.RegionResponse;
import com.example.multiregion_resilience.dto.RegionStatusRequest;
import com.example.multiregion_resilience.entity.Region;
import com.example.multiregion_resilience.enums.RegionStatus;
import com.example.multiregion_resilience.exception.ErrorCode;
import com.example.multiregion_resilience.exception.InvalidOperationException;
import com.example.multiregion_resilience.exception.ResourceAlreadyExistsException;
import com.example.multiregion_resilience.exception.ResourceNotFoundException;
import com.example.multiregion_resilience.mapper.RegionMapper;
import com.example.multiregion_resilience.repository.RegionRepository;
import com.example.multiregion_resilience.service.RegionService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class RegionServiceImpl implements RegionService{

	private final RegionRepository regionRepository;
	private final RegionMapper regionMapper;
	public RegionServiceImpl(RegionRepository regionRepository, RegionMapper regionMapper) {
		super();
		this.regionRepository = regionRepository;
		this.regionMapper = regionMapper;
	}
	@Override
	public RegionResponse createRegion(@Valid RegionRequest request) {
		// TODO Auto-generated method stub
		validateRegionRequest(request);
		String regionCode = request.getRegionCode().trim()
				.toUpperCase();
		if(regionRepository.existsByRegionCode(regionCode)) {
			throw new ResourceAlreadyExistsException(
					"Region already exists with code: "
					+regionCode,
					ErrorCode.REGION_ALREADY_EXISTS);
			
			
		}
		request.setRegionCode(regionCode);
		Region region = regionMapper.toEntity(request);
			
		if(region.getStatus()==null) {
			region.setStatus(RegionStatus.ACTIVE);
		}
		if(region.getEnabled()==null) {
			region.setEnabled(true);
		}
		Region savedRegion = regionRepository
				.save(region);
		return regionMapper.toResponse(savedRegion);
	}
	
	@Override
	public RegionResponse getRegionById(Long id) {
		// TODO Auto-generated method stub
		Region region = findRegionById(id);
		return regionMapper.toResponse(region);
	}
	@Override
	public RegionResponse getRegionByCode(String regioncode) {
		// TODO Auto-generated method stub
		if(regioncode == null || regioncode.isBlank()) {
			throw new InvalidOperationException(ErrorCode.INVALID_OPERATION,
					"Region code cannot be Empty");
		}
		Region region = regionRepository.findByRegionCode(
				regioncode.trim().toUpperCase())
				.orElseThrow(()->
				new ResourceNotFoundException(ErrorCode.REGION_NOT_FOUND,
						"Region not found with code: "+regioncode));
		return regionMapper.toResponse(region);
	}
	@Override
	public PageResponse<RegionResponse> getAllRegions(
			int page, int size, 
			RegionStatus status, Boolean enabled) {
		// TODO Auto-generated method stub
		validatePagination(page,size);
		Pageable pageable = PageRequest.of(page, size,
				Sort.by(Sort.Direction.ASC,"priority"));
		Page<Region> regionPage;
		if(status != null && enabled !=null) {
			regionPage = regionRepository
					.findByStatusAndEnabled(status, 
							enabled, pageable);
		}else if(status != null) {
			regionPage = regionRepository.findByStatus(
					status, pageable);
		}else if(enabled != null) {
			regionPage = regionRepository.findByEnabled
					(enabled, pageable);
		}else {
			regionPage = regionRepository.findAll(pageable);
		}
		
		return new PageResponse<>(
				regionPage.getContent().stream()
				.map(regionMapper::toResponse).toList(),
				regionPage.getNumber(),
				regionPage.getSize(),
				regionPage.getTotalElements(),
				regionPage.getTotalPages(),
				regionPage.isFirst(),
				regionPage.isLast()
				);
	}
	@Override
	public RegionResponse updateRegion(Long id, 
			@Valid RegionRequest request) {
		// TODO Auto-generated method stub
		validateRegionRequest(request);
		Region existingRegion = findRegionById(id);
		regionMapper.updateEntity(existingRegion, request);
		Region updatedRegion = regionRepository.save(
				existingRegion);
		return regionMapper.toResponse(updatedRegion);
	}
	@Override
	public RegionResponse updateRegionStatus(Long id, 
			@Valid RegionStatusRequest request) {
		// TODO Auto-generated method stub
		if(request == null || request.getStatus()==null) {
			throw new InvalidOperationException(
					ErrorCode.INVALID_REGION_STATUS,
					"Region status is required");
		}
		Region region = findRegionById(id);
		RegionStatus currentStatus = region.getStatus();
		RegionStatus newStatus = request.getStatus();
		
		validateStatusTransition(currentStatus, newStatus);
		region.setStatus(newStatus);
		if(newStatus == RegionStatus.INACTIVE) {
			region.setEnabled(false);
		}
		if(newStatus == RegionStatus.ACTIVE) {
			region.setEnabled(true);
		}
		Region updatedRegion = regionRepository.save(region);
		return regionMapper.toResponse(updatedRegion);
	}
	@Override
	public void deleteRegion(Long id) {
		// TODO Auto-generated method stub
		Region region = findRegionById(id);
		if(region.getStatus()==RegionStatus.ACTIVE) {
			throw new InvalidOperationException(
					ErrorCode.INVALID_OPERATION, 
					"Active region cannot be deleted. "
					+"Set the region to INACTIVE first."
							);
		}
		regionRepository.delete(region);
	}
	
    private Region findRegionById(
            Long id
    ) {

        if (id == null) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Region ID cannot be null"
            );
        }

        return regionRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                ErrorCode.REGION_NOT_FOUND,
                                "Region not found with id: "
                                        + id
                        )
                );
    }

    @Override
    public Region getRegionEntityByCode(
            String regionCode) {

        if (regionCode == null
                || regionCode.isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Region code cannot be empty"
            );
        }

        return regionRepository
                .findByRegionCode(
                        regionCode
                                .trim()
                                .toUpperCase()
                )
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                ErrorCode.REGION_NOT_FOUND,
                                "Region not found: "
                                + regionCode
                        )
                );
    }
    private void validateRegionRequest(
            RegionRequest request
    ) {

        if (request == null) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Region request cannot be null"
            );
        }
    }

    private void validatePagination(
            int page,
            int size
    ) {

        if (page < 0) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Page number cannot be negative"
            );
        }


        if (size <= 0 ||
                size > 100) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Page size must be between 1 and 100"
            );
        }
    }


    private void validateStatusTransition(
            RegionStatus currentStatus,
            RegionStatus newStatus
    ) {

        if (currentStatus == null) {
            return;
        }


        if (currentStatus ==
                newStatus) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_REGION_STATUS,
                    "Region is already in status: "
                            + newStatus
            );
        }
    }
	
}
