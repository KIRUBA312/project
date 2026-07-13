package com.example.enterprise_iam.security;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.repository.UserRepository;

@Service
public class OAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public OAuth2UserServiceImpl(UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
    public OAuth2User loadUser(OAuth2UserRequest request)
            throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate =
                new DefaultOAuth2UserService();

        OAuth2User oAuth2User = delegate.loadUser(request);

        String email = oAuth2User.getAttribute("email");

        if (email == null) {
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {

            user = new User();

            user.setEmail(email);

            user.setFirstName(oAuth2User.getAttribute("given_name"));

            user.setLastName(oAuth2User.getAttribute("family_name"));

            user.setPassword(
                    passwordEncoder.encode(UUID.randomUUID().toString()));

            user.setEnabled(true);
            user.setEmailVerified(true);
            user.setAccountNonLocked(true);
            user.setMfaEnabled(false);
            user.setFailedAttempts(0);

            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            userRepository.save(user);
        }

        return oAuth2User;
    }

}
