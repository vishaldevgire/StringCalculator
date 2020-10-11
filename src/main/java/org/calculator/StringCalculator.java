package org.calculator;

import java.util.Arrays;

public class StringCalculator {
    public long Add(String numbers) {
        String delimiter = ",\n";
        String input = numbers;

        if (numbers.startsWith("//")) {
            String[] tokens = numbers.split("\n", 2);
            delimiter = tokens[0].substring(2);
            input = tokens[1];
        }

        return numbers.isEmpty() ? 0L : Arrays
                .stream(input.split(String.format("[%s]", delimiter)))
                .mapToLong(Long::parseLong)
                .sum();
    }
}
