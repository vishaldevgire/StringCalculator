package org.calculator;

import org.calculator.util.Pair;

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

        Pair<String, String> parameters = parseInput(numbers);
        String delimiter = parameters.first();
        String input = parameters.second();

        validateInput(delimiter, input);

        return inputStream(delimiter, input).sum();
    }

    private void validateInput(String delimiter, String input) {
        List<Long> negativeNumbers = inputStream(delimiter, input).filter(number -> number < 0)
                .boxed()
                .collect(Collectors.toList());

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("negatives not allowed: %s", negativeNumbers)
            );
        }
    }

    private Pair<String, String> parseInput(String numbers) {
        String delimiter = ",\n";
        String input = numbers;

        if (numbers.startsWith("//")) {
            String[] tokens = numbers.split("\n", 2);
            delimiter = tokens[0].substring(2);
            input = tokens[1];
        }

        return Pair.of(delimiter, input);
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
