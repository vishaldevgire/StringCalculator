package org.calculator;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class InputParametersTest {

    @Test
    public void shouldCreateInputParametersWithDefaultDelimiter() {
        InputParameters parameters = InputParameters.from("11,22,33");

        assertThat(parameters.delimiterRegex(), is("[,\n]"));
        assertThat(parameters.numberString(), is("11,22,33"));
    }

    @Test
    public void shouldCreateInputParametersWithCustomDelimiter() {
        InputParameters parameters = InputParameters.from("//;\n11;22;33");

        assertThat(parameters.delimiterRegex(), is("[;]"));
    }

    @Test
    public void shouldCreateInputParametersWithCustomDelimiterHavingMultipleCharacters() {
        InputParameters parameters = InputParameters.from("//[***]\n22***33***55***66");

        assertThat(parameters.delimiterRegex(), is("\\Q***\\E"));
    }


    @Test
    public void shouldCreateInputParametersWithCustomMultipleDelimiters() {
        InputParameters parameters = InputParameters.from("//[***][++][..]\n22***33++55***66");

        assertThat(parameters.delimiterRegex(), is("\\Q***\\E|\\Q++\\E|\\Q..\\E"));
    }
}