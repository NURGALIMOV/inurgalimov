package ru.inurgalimov;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test
* @author Ilshat Nurgalimov
* @since 28.04.2018
*/

public class CalculateTest {
    /**
    * Test echo
    */
    @Test
    public void whenTakeNameThenTreeEchoPlusName() {
        String input = "Ilshat Nurgalimov";
        String expect = "Echo, echo, echo : Ilshat Nurgalimov"; 
        Calculate calc = new Calculate();
        String result = calc.echo(input);
        assertThat(result, is(expect));
    }
}