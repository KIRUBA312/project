package com.example.enterprise_iam.service;

import com.example.enterprise_iam.dto.request.EnableMfaRequestDto;
import com.example.enterprise_iam.dto.request.VerifyOtpRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;

import jakarta.validation.Valid;

public interface MFAService {

	ApiResponseDto generateOtp(@Valid EnableMfaRequestDto request);

	ApiResponseDto verifyOtp(@Valid VerifyOtpRequestDto request);

	ApiResponseDto disableMfa(String email);

}
