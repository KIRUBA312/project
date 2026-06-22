//package com.example.api_gateway.config;
//
//import java.nio.charset.StandardCharsets;
//
//import javax.crypto.SecretKey;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import io.jsonwebtoken.security.Keys;
//
//import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
//import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
//
//@Configuration
//public class ReactiveJwtDecoderConfig {
//
//    private static final String SECRET =
//            "mysecretkeymysecretkeymysecretkeymysecretkey";
//
//    @Bean
//    public ReactiveJwtDecoder reactiveJwtDecoder() {
//
//        SecretKey key =
//                Keys.hmacShaKeyFor(
//                        SECRET.getBytes(StandardCharsets.UTF_8));
//
//        return NimbusReactiveJwtDecoder
//                .withSecretKey(key)
//                .build();
//    }
//}
