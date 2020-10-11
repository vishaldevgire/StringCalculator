package org.calculator;


import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


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

    @Test
    public void Add_shouldThrowErrorWhenInputContainsNegativeNumbers() {
        StringCalculator calc = new StringCalculator();

        Throwable error1 = assertThrows(IllegalArgumentException.class, () -> calc.Add("23,-23,-11,33"));
        assertThat(error1.getMessage(), is("negatives not allowed: [-23, -11]"));

        Throwable error2 = assertThrows(IllegalArgumentException.class, () -> calc.Add("//;\n22;-33;55;66"));
        assertThat(error2.getMessage(), is("negatives not allowed: [-33]"));
    }

    @Test
    public void GetCalledCount_shouldReturnNumberOfTimesAddWasCalled() {
        StringCalculator calc = new StringCalculator();

        assertThat(calc.GetCalledCount(), is(0));

        calc.Add("//;\n22;33;55;66");

        assertThat(calc.GetCalledCount(), is(1));

        calc.Add("//-\n22-33-55");
        calc.Add("//.\n33.55");

        assertThat(calc.GetCalledCount(), is(3));
    }

    @Test
    public void Add_shouldIgnoreNumbersGreaterThan1000() {
        StringCalculator calc = new StringCalculator();

        assertThat(calc.Add("11000"), is(0L));
        assertThat(calc.Add("22000,1001,110000"), is(0L));

        assertThat(calc.Add("22000,11,1000,22,33,1001,4000"), is(1066L));
        assertThat(calc.Add("//;\n1;1001;2;12000;11000;3"), is(6L));
    }

    @Test
    public void Add_shouldReturnCorrectSumWithCustomDelimiterHavingMultipleCharacters() {
        StringCalculator calc = new StringCalculator();

        assertThat(calc.Add("//[***]\n22***33***55***66"), is(176L));
        assertThat(calc.Add("//[++]\n22++33++55"), is(110L));
    }

    @Test
    public void Add_shouldReturnCorrectSumWithMultipleCustomDelimiters() {
        StringCalculator calc = new StringCalculator();

        assertThat(calc.Add("//[***][++][&]\n22***33++55***66&12"), is(188L));
    }
}
