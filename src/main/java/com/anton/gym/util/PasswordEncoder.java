package com.anton.gym.util;

import java.util.Base64;

public class PasswordEncoder {
    public static String encodeString(String line) {
        return Base64.getEncoder().encodeToString(line.getBytes());
    }
}
