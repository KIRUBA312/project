package com.example.enterprise_iam.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(username).orElseThrow(() ->
		new UsernameNotFoundException("User not found : "+username));
		return new CustomUserDetails(user);
	}

}
