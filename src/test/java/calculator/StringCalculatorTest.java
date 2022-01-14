package calculator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {

    StringCalculator calculator = new StringCalculator();

    @Test
    void emptyList(){
        assertEquals(0F, calculator.resultFromListString(""));
    }

    @Test
    void oneNumberOnly(){
        assertEquals(4F,calculator.resultFromListString("4"));
        assertEquals(29F, calculator.resultFromListString("29"));
        assertEquals(950F, calculator.resultFromListString("950"));
    }

    @Test
    void twoNumbersByComa(){
        assertEquals(14F, calculator.resultFromListString("8,6"));
        assertEquals(17F, calculator.resultFromListString("10,7"));
    }

    @Test
    void twoNumbersByEndLine(){
        assertEquals(14F, calculator.resultFromListString("4\n10"));
        assertEquals(24F, calculator.resultFromListString("9\n15"));
    }

    @Test
    void testFirstLineDelimiters(){
        assertEquals(17F, calculator.resultFromListString("#\n7#10#4000"));
        assertEquals(60F, calculator.resultFromListString("-\n7-33-20"));
    }

    @Test
    void threeNumbersByAnyDelimiter(){
        assertEquals(10F, calculator.resultFromListString("#%7#!@1002##3"));
        assertEquals(245F, calculator.resultFromListString("%7/33%205"));
    }

    @Test
    void anyDelimiter(){
        assertEquals(21F, calculator.resultFromListString("=7#10/4"));
        assertEquals(1236F, calculator.resultFromListString("#450@780/6"));
        assertEquals(32F, calculator.resultFromListString("#24@1004/8"));
        assertEquals(102F, calculator.resultFromListString("#89@10/3"));
    }

    @Test
    void manyDelimiters(){
        assertEquals(145F, calculator.resultFromListString("4[##]89[!!]45[{}]7"));
        assertEquals(145F, calculator.resultFromListString("4[__#]89[^^!]45[%]7"));
        assertEquals(145F, calculator.resultFromListString("4[##]89[^^&!]45[!!%]7"));
    }

    @Test
    void notNegativesAllowed(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                calculator.resultFromListString("-3,-7"));
        assertEquals("Negative number found", exception.getMessage());
    }
}