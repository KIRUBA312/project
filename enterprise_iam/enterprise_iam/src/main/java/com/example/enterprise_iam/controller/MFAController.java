package com.example.enterprise_iam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.enterprise_iam.dto.request.EnableMfaRequestDto;
import com.example.enterprise_iam.dto.request.VerifyOtpRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.service.MFAService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mfa")
public class MFAController {

	@Autowired
	private MFAService mfaService;
	
	@PostMapping("/generate")
	public ResponseEntity<ApiResponseDto> generateOtp(
			@Valid @RequestBody EnableMfaRequestDto request){
		return ResponseEntity.ok(mfaService.generateOtp(request));
	}
	@PostMapping("/verify")
	public ResponseEntity<ApiResponseDto> verifyOtp(@Valid
			@RequestBody VerifyOtpRequestDto request)
	{
		return ResponseEntity.ok(mfaService.verifyOtp(request));
							
	}
	@PostMapping("/disable")
	public ResponseEntity<ApiResponseDto> disableMfa(@RequestParam String email)
	{
		return ResponseEntity.ok(mfaService.disableMfa(email));
	}
	
}
