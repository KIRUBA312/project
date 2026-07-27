package com.example.multiregion_resilience.util;

import java.util.UUID;

public final class RequestIdUtil {

    private static final ThreadLocal<String> REQUEST_ID =
            new ThreadLocal<>();

    private RequestIdUtil() {
    }

    public static String generateRequestId() {

        return UUID.randomUUID()
                .toString();
    }

    public static void setRequestId(
            String requestId) {

        REQUEST_ID.set(requestId);
    }

    public static String getRequestId() {

        String requestId =
                REQUEST_ID.get();

        if (requestId == null) {

            requestId =
                    generateRequestId();

            setRequestId(requestId);
        }

        return requestId;
    }

    public static void clearRequestId() {

        REQUEST_ID.remove();
    }
}