package com.example.api_monetization.util;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static boolean isBlank(String value) {

        return value == null || value.trim().isEmpty();

    }

}