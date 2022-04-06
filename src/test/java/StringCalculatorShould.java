import com.leanmind.ciscoadiz.stringcalculator.StringCalculator;
import org.junit.Test;


import static org.junit.Assert.*;

public class StringCalculatorShould {
    @Test
    public void add_two_numbers_separated_by_commas() throws Exception {
        int expected = 3;
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(expected, stringCalculator.addition("1,2"));
    }
    @Test
    public void for_empty_string_should_not_add() throws Exception {
        int expected = 0;
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(expected, stringCalculator.addition(""));
    }
    @Test
    public void allow_to_handle_new_lines_between_numbers() throws Exception {
        int expected = 6;
        assertEquals(expected, new StringCalculator().addition("1\n2,3"));
    }
    @Test
    public void support_different_delimiters() throws Exception {
        int expected = 3;
        assertEquals(expected, new StringCalculator().addition("//;\n1;2"));
    }
    @Test
    public void negative_numbers_not_allowed() {
        Throwable exception = assertThrows(Exception.class, () -> new StringCalculator().addition("-1,5,-8"));
        assertEquals("not allowed negatives: [-1, -8]", exception.getMessage());
    }

}
