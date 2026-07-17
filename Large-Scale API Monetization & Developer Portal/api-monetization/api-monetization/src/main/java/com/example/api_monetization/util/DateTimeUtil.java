package com.example.api_monetization.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static LocalDate today() {
        return LocalDate.now();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

}