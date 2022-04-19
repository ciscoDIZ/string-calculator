import com.leanmind.ciscoadiz.stringcalculator.StringCalculator;
import com.leanmind.ciscoadiz.stringcalculator.exception.NegativesNotAllowed;

import org.junit.Test;


import static org.junit.Assert.*;

public class StringCalculatorShouldTest {
    @Test
    public void operate_can_take_up_to_two_numbers_separated_by_commas() throws NegativesNotAllowed {
        int expected = 3;
        assertEquals(expected, new StringCalculator().sumNumbersIn("1,2"));
    }
    /*@Test
    public void sum_numbers_in_allow_sum_indeterminate_numbers_separated_by_commas() {
        int expected = 6;
        assertEquals(expected, new StringCalculator().sumNumbersIn("1,2,3"));
    }
    @Test
    public void allow_the_operate_method_to_handle_new_lines_between_numbers() throws NegativesNotAllowed {
        int expected = 6;
        assertEquals(expected, new StringCalculator().sumNumbersIn("1\n2,3"));
    }
    @Test
    public void allow_different_separator() throws NegativesNotAllowed {
        int expected = 6;
        assertEquals(expected, new StringCalculator().sumNumbersIn("//;\n1;2;3"));

    }
    @Test
    public void negative_number_will_throw_an_exception_negatives_not_allowed() {
        Throwable negativesNotAllowed = assertThrows(
                NegativesNotAllowed.class,
                () -> new StringCalculator().sumNumbersIn("2,-8,1,-5")
        );
        assertEquals(
                "you can't add up negative numbers. affected numbers: [-8, -5]",
                negativesNotAllowed.getMessage()
        );
    }
    @Test
    public void numbers_bigger_than_1000_should_be_ignored() throws NegativesNotAllowed {
        int expected = 2;
        assertEquals(expected, new StringCalculator().sumNumbersIn("1001,2"));
    }
    @Test
    public void delimiters_can_be_of_any_length() throws NegativesNotAllowed {
        int expected = 6;
        assertEquals(expected, new StringCalculator().sumNumbersIn("//[***]\n1***2***3"));
    }
    @Test
    public void allow_multiple_delimiters() throws NegativesNotAllowed {
        int expected = 6;
        assertEquals(expected, new StringCalculator().sumNumbersIn("//[;][*]\n1;2*3"));
    }*/
}
