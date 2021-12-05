package org.koxx4.utilities;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsSame;
import org.junit.Test;
import org.hamcrest.CoreMatchers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleStateEquationFormatterTest {

    private final SimpleStateEquationFormatter formatter = new SimpleStateEquationFormatter();

    @Test
    public void format_ProducesValidHtml() {
        String input = "(s1)";
        String expectedOutput = "<font color=#[0-9a-fA-F]{6}><b>[(]</b></font>s1<font color=#[0-9a-fA-F]{6}><b>[)]</b></font>";
        assertTrue(formatter.format(input).matches(expectedOutput));
    }

    @Test
    public void format_ParenthesesColorsMatch(){
        String input = "(s0(x0s1))";
        String output = formatter.format(input);
        // Should be like:
        //<font color=#000000><b>(</b></font>s0<font color=#000000><b>(</b></font>x0s1<font color=#000000><b>)</b></font><font color=#000000><b>)</b></font>
        String firstOpeningParColor = output.substring(14,20);
        String secondOpeningParColor = output.substring(51, 57);
        String firstClosingParColor = output.substring(90, 96);
        String secondClosingParColor = output.substring(125, 131);

        assertThat(firstOpeningParColor, is(secondClosingParColor));
        assertThat(secondOpeningParColor, is(firstClosingParColor));
    }

    @Test
    public void format_DoestNothingWhenInputEmpty(){
        assertEquals("", formatter.format(""));
    }

    @Test
    public void format_ProducesValidOutputWithoutParentheses(){
        assertEquals("abv123./", formatter.format("abv123./"));
        assertEquals("1234dasc./", formatter.format("1234dasc./"));
    }

}