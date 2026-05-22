package com.example.financialservice.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.financialservice.entity.User;
import com.example.financialservice.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String username)
	throws UsernameNotFoundException{
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username)
				.orElseThrow(() ->
				new UsernameNotFoundException("User not found"));
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
		return new org.springframework.security.core.userdetails.
				User(
				user.getUsername(), user.getPassword(), authorities);
	}

}
