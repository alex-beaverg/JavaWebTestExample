package com.beaverg.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringTransform {

    public static String capitalizeWords(String input) {
        return Arrays.stream(input.split("\\s"))
                .map(word -> Character.toTitleCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
