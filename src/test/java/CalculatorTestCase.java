import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
    @DisplayName("Check multiplication with zero")
    void testMultiplyWithZero(){
        int result = calculator.multiply(0, 20);
        assertEquals(result, 0);
    }

    @Test
    @DisplayName("Check multiplication with negative numbers")
    void testMultiplyWithNegative(){
        int result = calculator.multiply(-4, 20);
        assertEquals(result, -80);
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
    @DisplayName("Check sum with negative values")
    void testSumWithNegative(){
        double result = calculator.sum(-10.5, 20.3);
        assertEquals(result, 9.8);

        double result2 = calculator.sum(-10.5, -20.3);
        assertEquals(result2, -30.8);
    }
    
    @Test
    @DisplayName("Check discount application")
    void testDiscount(){
        double result = calculator.discount(100, 35);
        assertEquals(result, 65);
    }

    @Test
    @DisplayName("Check discount with zero percent")
    void testDiscountWithZeroPercent(){
        double result = calculator.discount(100, 0);
        assertEquals(result, 100);

        double result2 = calculator.discount(100, 100);
        assertEquals(result2, 0);
    }

    @Test
    @DisplayName("Check discount with invalid percentage")
    void testDiscountInvalidPercentage(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.discount(100, 150);
        });
        assertEquals("Percentage must be between 0 and 100", exception.getMessage());
    }

    @Test
    @DisplayName("Check discount with negative percentage")
    void testDiscountNegativePercentage(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.discount(100, -10);
        });
        assertEquals("Percentage must be between 0 and 100", exception.getMessage());
    }

    @Test
    @DisplayName("Check total calculation of a list of amounts")
    void testCalculateTotal(){
        List<Double> amounts = new ArrayList<>();
        amounts.add(10.0);
        amounts.add(20.0);
        amounts.add(30.0);
        double totalAmount = calculator.calculateTotal(amounts);
        assertEquals(totalAmount, 60.0);
    }

    @Test
    @DisplayName("Check total calculation with empty list")
    void testCalculateTotalWithEmptyList(){
        List<Double> amounts = new ArrayList<>();
        double totalAmount = calculator.calculateTotal(amounts);
        assertEquals(totalAmount, 0.0);
    }



}