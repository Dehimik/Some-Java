package com.dehimik.utils;

public class InputValidator {
    public static String cleanName(String name) {
        return name.replaceAll("[^a-zA-Zа']", "").trim();
    }

    public static boolean isValidPhoneNumber(String phone) {
        return phone.matches("^\\+?\\d{10,13}$");
    }
}
