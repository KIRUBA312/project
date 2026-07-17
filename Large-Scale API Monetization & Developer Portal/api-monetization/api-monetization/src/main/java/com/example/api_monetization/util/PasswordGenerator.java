package com.example.api_monetization.util;

import java.security.SecureRandom;

public final class PasswordGenerator {

    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";

    private static final SecureRandom RANDOM = new SecureRandom();

    private PasswordGenerator() {
    }

    public static String generate(int length) {

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<length;i++) {

            sb.append(
                    CHARACTERS.charAt(
                            RANDOM.nextInt(CHARACTERS.length())
                    )
            );
        }

        return sb.toString();
    }

}