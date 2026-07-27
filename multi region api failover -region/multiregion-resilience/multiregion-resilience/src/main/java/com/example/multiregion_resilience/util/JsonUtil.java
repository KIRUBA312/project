package com.example.multiregion_resilience.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public JsonUtil( ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }

    public String toJson(
            Object object) {

        try {

            return objectMapper.writeValueAsString(
                    object
            );

        } catch (JsonProcessingException e) {

            throw new IllegalArgumentException(
                    "Unable to convert object to JSON",
                    e
            );
        }
    }

    public <T> T fromJson( String json, Class<T> targetClass) {

        try {
            return objectMapper.readValue(
                    json,
                    targetClass
            );
        } catch (JsonProcessingException e) {

            throw new IllegalArgumentException(
                    "Unable to convert JSON to object",
                    e
            );
        }
    }
}