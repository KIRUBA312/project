package com.example.resource_service.security;

import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

@Configuration
public class JwtAuthenticationConverterConfig {

	@Bean
	Converter<Jwt, Collection<GrantedAuthority>> authoritiesConverter(){
		return jwt ->{
			List<String> roles = jwt.getClaimAsStringList("roles");
			return roles.stream()
					.map(role ->
					new SimpleGrantedAuthority("ROLE_"+role))
					.map(a ->(GrantedAuthority) a)
					.toList();
		};
	}
}
