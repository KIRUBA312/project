package com.example.api_monetization.util;

public final class MaskingUtil {

    private MaskingUtil() {
    }

    public static String maskApiKey(String apiKey) {

        if(apiKey == null || apiKey.length() < 8) {
            return "****";
        }

        return apiKey.substring(0,4)
                + "********"
                + apiKey.substring(apiKey.length()-4);
    }

}