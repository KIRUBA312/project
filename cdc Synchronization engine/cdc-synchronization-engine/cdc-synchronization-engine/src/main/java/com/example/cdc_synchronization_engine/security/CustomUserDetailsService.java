package com.example.cdc_synchronization_engine.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cdc_synchronization_engine.entity.User;
import com.example.cdc_synchronization_engine.entity.UserRole;
import com.example.cdc_synchronization_engine.repository.UserRepository;
import com.example.cdc_synchronization_engine.repository.UserRoleRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    public CustomUserDetailsService(
            UserRepository userRepository,
            UserRoleRepository userRoleRepository) {

        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found : " + username));

        List<UserRole> userRoles =
                userRoleRepository.findByUserId(user.getId());

        return new CustomUserDetails(

                user.getUsername(),

                user.getPassword(),

                Boolean.TRUE.equals(user.getEnabled()),

                userRoles.stream()

                        .map(userRole ->
                                new SimpleGrantedAuthority(
                                        "ROLE_" +
                                        userRole.getRole().getRoleName()))

                        .collect(Collectors.toSet())

        );
    }

}