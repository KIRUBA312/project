package com.example.api_monetization.util;

import java.time.LocalDate;
import java.util.UUID;

public final class InvoiceNumberGenerator {

    private InvoiceNumberGenerator() {
    }

    public static String generate() {

        return "INV-"
                + LocalDate.now().getYear()
                + "-"
                + UUID.randomUUID()
                        .toString()
                        .substring(0,8)
                        .toUpperCase();
    }
}