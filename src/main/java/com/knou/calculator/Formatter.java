package com.knou.calculator;

public class Formatter {
    public static String format(double result) {
        if (result % 1 == 0) {
            return String.valueOf((int) result);
        } else {
            String formatted = String.format("%.2f", result);
            if (formatted.endsWith("0")) {
                formatted = formatted.substring(0, formatted.length() - 1);
            }
            return formatted;
        }
    }
}
