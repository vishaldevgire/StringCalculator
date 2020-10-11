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

        InputParameters parameters = InputParameters.from(numbers);

        validateInput(parameters);

        return inputStream(parameters).sum();
    }

    private void validateInput(InputParameters parameters) {
        List<Long> negativeNumbers = inputStream(parameters)
                .filter(number -> number < 0)
                .boxed()
                .collect(Collectors.toList());

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("negatives not allowed: %s", negativeNumbers)
            );
        }
    }

    private LongStream inputStream(InputParameters parameters) {
        return Arrays
                .stream(parameters.numberString().split(parameters.delimiterRegex()))
                .mapToLong(Long::parseLong)
                .filter(number -> number < 1001);
    }

    public Integer GetCalledCount() {
        return numberOfTimesAddCalled;
    }
}
