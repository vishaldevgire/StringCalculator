package org.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParameters {
    private final boolean hasCustomDelimiter;
    private List<String> delimiters;
    private String numbers;

    public InputParameters(List<String> delimiters, String numbers, boolean hasCustomDelimiter) {
        this.delimiters = delimiters;
        this.numbers = numbers;
        this.hasCustomDelimiter = hasCustomDelimiter;
    }

    public static InputParameters from(String inputString) {
        List<String> delimeters = Arrays.asList(",\n");
        String numbers = inputString;
        boolean hasCustomDelimiter = false;

        if (inputString.startsWith("//")) {
            String[] tokens = inputString.split("\n", 2);

            if (inputString.startsWith("//[")) {
                hasCustomDelimiter = true;
                String delimiterString = tokens[0].substring(3, tokens[0].length() - 1);
                delimeters = Arrays.asList(delimiterString.split("\\Q][\\E"));
            } else  {
                delimeters = Arrays.asList(tokens[0].substring(2));
            }

            numbers = tokens[1];
        }

        return new InputParameters(delimeters, numbers, hasCustomDelimiter);
    }

    public String numberString() {
        return numbers;
    }

    public String delimiterRegex() {
        if (hasCustomDelimiter) {
            return delimiters
                    .stream()
                    .map(del -> String.format("\\Q%s\\E", del))
                    .collect(Collectors.joining("|"));
        }
        return String.format("[%s]", delimiters.get(0));
    }
}
