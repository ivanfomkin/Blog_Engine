package ru.skillbox.ifomkin.diplom.utils;

public class RandomStringGenerator {
    private static final String UPPER_CASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_ALPHABET = UPPER_CASE_ALPHABET.toLowerCase();
    private static final String NUMBERS = "0123456789";
    private static final String NUMBERS_AND_UPPER_CASE_ALPHABET = NUMBERS + UPPER_CASE_ALPHABET;
    private static final String ALL_SYMBOLS = UPPER_CASE_ALPHABET + LOWER_CASE_ALPHABET + NUMBERS;

    public static String generateUpperCaseAndNumbersString(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(NUMBERS_AND_UPPER_CASE_ALPHABET.charAt((int)
                    (Math.random() * NUMBERS_AND_UPPER_CASE_ALPHABET.length())));
        }
        return builder.toString();
    }

    public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(ALL_SYMBOLS.charAt((int) (Math.random() * ALL_SYMBOLS.length())));
        }
        return stringBuilder.toString();
    }
}
