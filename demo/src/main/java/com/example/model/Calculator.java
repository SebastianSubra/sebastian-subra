package com.example.model;

import java.util.List;

public class Calculator {
    public static final String EMPTY = "empty";

    // Multiply two numbers
    public static double multiply(double a, double b) {
        return a * b;
    }

    // Concatenate two strings, handling null safely
    public static String concat(String a, String b) {
    if (a == null && b == null) {
        return EMPTY;
    }
    if (a == null) {
        return EMPTY;
    }
    if (b == null) {
        return EMPTY;
    }
    return a + " " + b;
    }


    // Add two values
    public static double sum(double a, double b) {
        return a + b;
    }

    // Apply a percentage discount
    public static double discount(double amount, double percent) {
        if (percent < 0) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
        if (percent > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
        return amount - (amount * percent / 100.0);
    }

    // Calculate total of list of amounts
    public static double calculateTotal(List<Double> amounts) {
        double total = 0.0;
        for (Double value : amounts) {
            total += value;
        }
        return total;
    }
}
