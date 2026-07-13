package com.example.enterprise_iam.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprise_iam.dto.request.EnableMfaRequestDto;
import com.example.enterprise_iam.dto.request.VerifyOtpRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.entity.MFASecret;
import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.exception.ResourceNotFoundException;
import com.example.enterprise_iam.repository.MFASecretRepository;
import com.example.enterprise_iam.repository.UserRepository;
import com.example.enterprise_iam.service.MFAService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class MFAServiceImpl implements MFAService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MFASecretRepository mfaSecretRepository;
	
	private String generateOtp() {
		Random random = new Random();
		return String.valueOf(100000+random.nextInt(900000));
	}
	
	@Override
	public ApiResponseDto generateOtp(@Valid EnableMfaRequestDto request) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() ->
				new ResourceNotFoundException("User not found"));
		String otp = generateOtp();
		MFASecret secret = mfaSecretRepository.findByUser(user)
				.orElse(new MFASecret());
		secret.setUser(user);
		secret.setSecret(otp);
		secret.setEnabled(false);
		
		mfaSecretRepository.save(secret);
		
		System.out.println("=======================================");
		System.out.println("MFA OTP");
		System.out.println("User : "+user.getEmail());
		System.out.println("OTP : "+otp);
		System.out.println("=======================================");
		
		return new ApiResponseDto(true,
				"OTP generated successfully. Check console");
		
	}
	@Override
	public ApiResponseDto verifyOtp(@Valid VerifyOtpRequestDto request) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(()->
				new ResourceNotFoundException("User not found"));
		MFASecret secret = mfaSecretRepository.findByUser(user)
				.orElseThrow(() ->
				new ResourceNotFoundException("OTP not generated"));
		if(!secret.getSecret().equals(request.getOtp())) {
			return new ApiResponseDto(false,"Invalid OTP");
		}
		secret.setEnabled(true);
		mfaSecretRepository.save(secret);
		user.setMfaEnabled(true);
		return new ApiResponseDto(true,
				"MFA Enabled Successfully");
	}
	@Override
	public ApiResponseDto disableMfa(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email).orElseThrow(() ->
		new ResourceNotFoundException("User not found"));
		MFASecret secret = mfaSecretRepository.findByUser(user)
				.orElseThrow(() ->
				new ResourceNotFoundException("MFA not enabled"));
		secret.setEnabled(false);
		secret.setSecret(null);
		
		mfaSecretRepository.save(secret);
		user.setMfaEnabled(false);
		userRepository.save(user);
		return new ApiResponseDto(true,
				"MFA Disabled Successfully");
	}
	
}
