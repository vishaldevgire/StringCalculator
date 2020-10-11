package org.calculator;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class InputParametersTest {

    @Test
    public void shouldCreateInputParametersWithDefaultDelimiter() {
        InputParameters parameters = InputParameters.from("11,22,33");

        MatcherAssert.assertThat(parameters.delimiterRegex(), Matchers.is("[,\n]"));
        MatcherAssert.assertThat(parameters.numberString(), Matchers.is("11,22,33"));
    }

    @Test
    public void shouldCreateInputParametersWithCustomDelimiter() {
        InputParameters parameters = InputParameters.from("//;\n11;22;33");

        MatcherAssert.assertThat(parameters.delimiterRegex(), Matchers.is("[;]"));
    }

    @Test
    public void shouldCreateInputParametersWithCustomDelimiterHavingMultipleCharacters() {
        InputParameters parameters = InputParameters.from("//[***]\n22***33***55***66");

        MatcherAssert.assertThat(parameters.delimiterRegex(), Matchers.is("\\Q***\\E"));
    }
}