package org.calculator;

import java.util.Arrays;
import java.util.stream.LongStream;

public class StringCalculator {
    public long Add(String numbers) {
        String delimiter = ",\n";
        String input = numbers;

        if (numbers.startsWith("//")) {
            String[] tokens = numbers.split("\n", 2);
            delimiter = tokens[0].substring(2);
            input = tokens[1];
        }

        if (inputStream(delimiter, input).filter(number -> number < 0).findAny().isPresent()) {
            throw new IllegalArgumentException("negatives not allowed");
        }

        return numbers.isEmpty() ? 0L : inputStream(delimiter, input).sum();
    }

    private LongStream inputStream(String delimiter, String input) {
        return Arrays
                .stream(input.split(String.format("[%s]", delimiter)))
                .mapToLong(Long::parseLong);
    }
}
