package org.calculator;

public class InputParameters {
    private boolean multiCharacterDelimiter;
    private String delimiter;
    private String numbers;

    public InputParameters(String delimiter, String numbers) {
        this.delimiter = delimiter;
        this.numbers = numbers;
        this.multiCharacterDelimiter = false;
    }

    public static InputParameters from(String inputString) {
        String delimiter = ",\n";
        String numbers = inputString;

        if (inputString.startsWith("//")) {
            String[] tokens = inputString.split("\n", 2);
            delimiter = tokens[0].substring(2);
            numbers = tokens[1];
        }

        return new InputParameters(delimiter, numbers);
    }

    public String delimiter() {
        return delimiter;
    }

    public String numberString() {
        return numbers;
    }
}
