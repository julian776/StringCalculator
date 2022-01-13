package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {

    StringCalculator calculator = new StringCalculator();

    @Test
    void emptyList(){
        assertEquals(0F, calculator.resultByList(""));
    }

    @Test
    void oneNumberOnly(){
        assertEquals(4F,calculator.resultByList("4"));
        assertEquals(9F, calculator.resultByList("9"));
    }

    @Test
    void twoNumbersByComa(){
        assertEquals(14F, calculator.resultByList("8,6"));
        assertEquals(17F, calculator.resultByList("10,7"));
    }

    @Test
    void twoNumbersByEndLine(){
        assertEquals(14F, calculator.resultByList("4\n10"));
        assertEquals(24F, calculator.resultByList("9\n15"));
    }

    @Test
    void threeNumbers(){
        assertEquals(21F, calculator.resultByList("4#7@10"));
        assertEquals(49F, calculator.resultByList("9/7#33"));
    }

    @Test()
    void notNegativesAllowed(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                calculator.resultByList("-3, -7"));
        assertEquals("", exception.getMessage());
    }



}