package com.example.apigateway.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.entity.RequestLog;
import com.example.apigateway.entity.User;
import com.example.apigateway.repository.UserRepository;
import com.example.apigateway.service.LogService;
import com.example.apigateway.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LogService logService;

	@Override
	public UserResponseDto createUser(UserRequestDto userRequestDto) {
		// TODO Auto-generated method stub
		User user = new User();
		
		user.setName(userRequestDto.getName());
		user.setEmail(userRequestDto.getEmail());
		user.setCity(userRequestDto.getCity());
		
		User saveUser = userRepository.save(user);
		saveLog("/api/users","POST");
		
		return maptoDto(saveUser);
	}

	@Override
	public UserResponseDto getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow();
		saveLog("/api/users/"+id, "GET");
		return maptoDto(user);
	}

	@Override
	public List<UserResponseDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = userRepository.findAll();
		List<UserResponseDto> responseList = new ArrayList<>();
		
		 for(User user: users) {
			 responseList.add(maptoDto(user));
		 }
		 
		 saveLog("/api/users","GET");
		 
		 return responseList;
	
	}

	@Override
	public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findById(id).orElseThrow();
		
		user.setName(userRequestDto.getName());
		user.setEmail(userRequestDto.getEmail());
		user.setCity(userRequestDto.getCity());
		
		User updatedUser = userRepository.save(user);
		saveLog("/api/users/"+id, "PUT");
		
		return maptoDto(updatedUser);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
		saveLog("/api/users/"+id, "DELETE");
		
	}
	
	private UserResponseDto maptoDto(User user) {
		UserResponseDto userResponseDto = new UserResponseDto();
		
		userResponseDto.setId(user.getId());
		userResponseDto.setName(user.getName());
		userResponseDto.setEmail(user.getEmail());
		userResponseDto.setCity(user.getCity());
		
		return userResponseDto;
	}
	
	private void saveLog(String path, String method) {
		// TODO Auto-generated method stub
		
		RequestLog log = new RequestLog();
		
		log.setRequestPath(path);
		log.setMethod(method);
		log.setTimestamp(LocalDateTime.now());
		log.setStatus("SUCCESS");
		
		logService.saveLog(log);
		
	}
}
