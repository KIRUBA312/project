package com.example.multiregion_resilience.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class IdempotencyUtil {

    private final ObjectMapper objectMapper;

    public IdempotencyUtil(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }

    public String generateRequestHash(
            Object request) {

        if (request == null) {

            return generateHash("NULL");
        }
        try {
            String json =objectMapper.writeValueAsString(
                            request
                    );
            return generateHash(json);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException(
                    "Unable to serialize request for idempotency hash",
                    exception
            );
        }
    }

    public String generateRequestHash(
            String requestBody) {

        if (requestBody == null) {

            requestBody = "NULL";
        }
        return generateHash(requestBody);
    }
    private String generateHash( String value) {
        try {

            MessageDigest digest =
                    MessageDigest.getInstance(
                            "SHA-256"
                    );

            byte[] hash =
                    digest.digest(
                            value.getBytes(
                                    StandardCharsets.UTF_8
                            )
                    );

            StringBuilder hexString =
                    new StringBuilder();

            for (byte b : hash) {

                String hex =
                        Integer.toHexString(
                                0xff & b
                        );

                if (hex.length() == 1) {

                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException exception) {

            throw new IllegalStateException(
                    "SHA-256 algorithm not available",
                    exception
            );
        }
    }
}