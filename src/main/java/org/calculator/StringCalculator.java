package org.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class StringCalculator {
    private Integer numberOfTimesAddCalled = 0;

    public long Add(String numbers) {
        numberOfTimesAddCalled += 1;

        if (numbers.isEmpty()) {
            return 0L;
        }

        String delimiter = ",\n";
        String input = numbers;

        if (numbers.startsWith("//")) {
            String[] tokens = numbers.split("\n", 2);
            delimiter = tokens[0].substring(2);
            input = tokens[1];
        }

        List<Long> negativeNumbers = inputStream(delimiter, input).filter(number -> number < 0).boxed().collect(Collectors.toList());
        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("negatives not allowed: %s", negativeNumbers)
            );
        }

        return inputStream(delimiter, input).sum();
    }

    private LongStream inputStream(String delimiter, String input) {
        return Arrays
                .stream(input.split(String.format("[%s]", delimiter)))
                .mapToLong(Long::parseLong)
                .filter(number -> number < 1001);
    }

    public Integer GetCalledCount() {
        return numberOfTimesAddCalled;
    }
}
