import com.leanmind.ciscoadiz.stringcalculator.StringCalculator;
import org.junit.Assert;
import org.junit.Test;


import static org.junit.Assert.*;

public class StringCalculatorShould {
    @Test
    public void operate_can_take_up_to_two_numbers_separated_by_commas() {
        int expected = 3;
        assertEquals(expected, new StringCalculator().operate("1,2"));
    }
}
