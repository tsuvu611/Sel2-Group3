package common;

import java.security.SecureRandom;
import java.util.Random;

public class Utility {
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String pathRegex = "/";

    public static String randomString(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    public static String randomSpecialCharExA(int iLength) {
        final String alphabet = "/:*?<>|\\\"#[]{}=%";
        final int N = alphabet.length();
        Random rd = new Random();
        StringBuilder sb = new StringBuilder(iLength);
        for (int i = 0; i < iLength; i++) {
            sb.append(alphabet.charAt(rd.nextInt(N)));
        }
        return sb.toString();
    }

    public static int randomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

}
