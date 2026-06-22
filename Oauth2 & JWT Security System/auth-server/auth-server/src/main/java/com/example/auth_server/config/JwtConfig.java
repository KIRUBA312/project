package com.example.auth_server.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

@Configuration
public class JwtConfig {

    @Bean
    public JwtEncoder jwtEncoder() throws Exception {

        KeyPairGenerator generator =
                KeyPairGenerator.getInstance("RSA");

        generator.initialize(2048);

        KeyPair keyPair =
                generator.generateKeyPair();

        RSAKey rsaKey =
                new RSAKey.Builder(
                        (java.security.interfaces.RSAPublicKey)
                                keyPair.getPublic())
                        .privateKey(
                                (java.security.interfaces.RSAPrivateKey)
                                        keyPair.getPrivate())
                        .build();

        return new NimbusJwtEncoder(
                new ImmutableJWKSet<>(
                        new JWKSet(rsaKey)));
    }
}