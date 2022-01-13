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
        assertEquals(21F, calculator.resultFromListString("#\n7#10#4"));
        assertEquals(60F, calculator.resultFromListString("-\n7-33-20"));
    }

    @Test
    void threeNumbersByAnyDelimiter(){
        assertEquals(21F, calculator.resultFromListString("#%7#!@10##$3"));
        assertEquals(49F, calculator.resultFromListString("%7$33$$%205"));
    }

    @Test
    void anyDelimiter(){
        assertEquals(21F, calculator.resultFromListString("*7$$#10/3"));
        assertEquals(21F, calculator.resultFromListString("#7@10/3"));
        assertEquals(21F, calculator.resultFromListString("#7@10/3"));
        assertEquals(21F, calculator.resultFromListString("#7@10/3"));
    }

    @Test
    void notNegativesAllowed(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                calculator.resultFromListString("-3,-7"));
        assertEquals("Negative number found", exception.getMessage());
    }

    @Test
    void any(){
        Arrays.stream("$\n7$33$20".split("\n$")).forEach(System.out::println);
    }
}