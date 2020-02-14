package com.company;

import java.util.UUID;

public class RandomString {
    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }
}
