package org.calculator;

import java.util.Arrays;

public class StringCalculator {
    public long Add(String numbers) {
        return numbers.isEmpty() ? 0L : Arrays
                .stream(numbers.split("[,\n]"))
                .mapToLong(Long::parseLong)
                .sum();
    }
}
