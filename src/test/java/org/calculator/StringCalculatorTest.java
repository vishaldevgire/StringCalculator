package org.calculator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class StringCalculatorTest {

    @Test
    public void Add_shouldReturnZeroWhenInputIsEmpty() {
        StringCalculator calc = new StringCalculator();

        assertThat(calc.Add(""), is(0L));
    }

    @Test
    public void Add_shouldReturnCorrectSumWhenInputIsSingleNumber() {
        StringCalculator calc = new StringCalculator();

        assertThat(calc.Add("33"), is(33L));
    }

    @Test
    public void Add_shouldReturnCorrectSumWhenInputContainsMultipleNumbers() {
        StringCalculator calc = new StringCalculator();

        assertThat(calc.Add("11,22,33"), is(66L));
    }

    @Test
    public void Add_shouldReturnCorrectSumWhenInputIsSeparatedByNewLineCharacters() {
        StringCalculator calc = new StringCalculator();

        assertThat(calc.Add("11\n22,33\n55\n66"), is(187L));
    }

    @Test
    public void Add_shouldReturnCorrectSumWithCustomDelimiter() {
        StringCalculator calc = new StringCalculator();

        assertThat(calc.Add("//;\n22;33;55;66"), is(176L));
        assertThat(calc.Add("//-\n22-33-55"), is(110L));
        assertThat(calc.Add("//.\n33.55"), is(88L));
    }
}