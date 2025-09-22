import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Calculator;

public class CalculatorTestCase{
    
    Calculator calculator;

    @BeforeEach
    void setUp(){
        calculator = new Calculator();

    }

    @Test
    @DisplayName("Simple multiplication shuould work")
    void testMultiply(){
        int result = calculator.multiply(4,20);
        assertEquals(result, 80);
    }

    @Test
    @DisplayName("Check concatenation of two strings")
    void testConcat(){
        String result = calculator.concat("Carlos", "Ramirez");
        assertEquals(result, "Carlos Ramirez");
    }

    @Test
    @DisplayName("Check concatenation of two strings but one is null")
    void testConcatNull(){
        String result = calculator.concat(null, "Ramirez");
        assertEquals("empty", result);
    }

    @Test
    @DisplayName("Check sum of two double values")
    void testSum(){
        double result = calculator.sum(10.5, 20.3);
        assertEquals(result, 30.8);
    } 
    
    @Test
    @DisplayName("Check discount application")
    void testDiscount(){
        double result = calculator.discount(100, 35);
        assertEquals(result, 65);
    }

    @Test
    @DisplayName("Check discount with invalid percentage")
    void testDiscountInvalidPercentage(){
       double result = calculator.discount(100, -1);
       assertEquals(result, java.lang.IllegalArgumentException.class);
       /*assertThrows(IllegalArgumentException.class, () -> {
           calculator.discount(100, 150);*/
    }

    @Test
    @DisplayName("Check total calculation of a list of amounts")
    void testCalculateTotal(){
        List<Double> amounts = calculator.calculateTotal();

    }

}