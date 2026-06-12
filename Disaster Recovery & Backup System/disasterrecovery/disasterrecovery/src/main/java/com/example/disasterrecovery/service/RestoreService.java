package com.example.disasterrecovery.service;

import java.util.List;


import com.example.disasterrecovery.dto.RestoreRequestDto;
import com.example.disasterrecovery.dto.RestoreResponseDto;
import com.example.disasterrecovery.dto.RestoreStatusDto;

public interface RestoreService {

	RestoreResponseDto restoreBackup(RestoreRequestDto dto);

	RestoreStatusDto getRestoreStatus(Long id);

	List<RestoreResponseDto> getAllRestoreLogs();

	RestoreResponseDto getRestoreById(Long id);

	RestoreResponseDto updateRestore(Long id, RestoreRequestDto dto);

	String deleteRestoreLog(Long id);

}
