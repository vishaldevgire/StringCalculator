package org.calculator;

public class InputParameters {
    private boolean multiCharacterDelimiter;
    private String delimiter;
    private String numbers;

    public InputParameters(String delimiter, String numbers, boolean multiCharacterDelimiter) {
        this.multiCharacterDelimiter = multiCharacterDelimiter;
        this.delimiter = delimiter;
        this.numbers = numbers;
    }

    public static InputParameters from(String inputString) {
        String delimiter = ",\n";
        String numbers = inputString;
        boolean multiCharacterDelimiter = false;

        if (inputString.startsWith("//")) {
            String[] tokens = inputString.split("\n", 2);

            if (inputString.startsWith("//[")) {
                multiCharacterDelimiter = true;
                delimiter = tokens[0].substring(3, tokens[0].length() - 1);
            } else  {
                delimiter = tokens[0].substring(2);
            }

            numbers = tokens[1];
        }

        return new InputParameters(delimiter, numbers, multiCharacterDelimiter);
    }

    public String numberString() {
        return numbers;
    }

    public String delimiterRegex() {
        return String.format(multiCharacterDelimiter ? "\\Q%s\\E" : "[%s]", delimiter);
    }
}
