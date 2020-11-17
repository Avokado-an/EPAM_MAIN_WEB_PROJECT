package com.anton.gym.util;

import java.util.Base64;

/**
 * The {@code PasswordEncoder} class represents PasswordEncoder.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class PasswordEncoder {
    /**
     * encodes string
     * @param line the line to encode
     * @return the encoded string
     */
    public static String encodeString(String line) {
        return Base64.getEncoder().encodeToString(line.getBytes());
    }
}
