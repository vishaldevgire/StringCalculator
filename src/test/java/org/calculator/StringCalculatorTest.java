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
}