import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.model.Calculator;

public class CalculatorTestCase {

    // Multiply
    @Test
    @DisplayName("Multiply should handle positive, zero, and negative numbers")
    void testMultiply() {
        assertEquals(80.0, Calculator.multiply(4, 20));
        assertEquals(0.0, Calculator.multiply(0, 20));
        assertEquals(-80.0, Calculator.multiply(-4, 20));
    }

    // Concat
    @Test
    @DisplayName("Concat should handle nulls and normal strings")
    void testConcat() {
        assertEquals("A B", Calculator.concat("A", "B"));
        assertEquals(Calculator.EMPTY, Calculator.concat(null, "B"));
        assertEquals(Calculator.EMPTY, Calculator.concat("A", null));
        assertEquals(Calculator.EMPTY, Calculator.concat(null, null));
    }

    // Sum
    @Test
    @DisplayName("Sum should handle positive and negative numbers")
    void testSum() {
        assertEquals(30.8, Calculator.sum(10.5, 20.3), 0.001);
        assertEquals(9.5, Calculator.sum(-10.5, 20), 0.001);
        assertEquals(-30.8, Calculator.sum(-10.5, -20.3), 0.001);
    }

    // Discount
    @Test
    @DisplayName("Discount should apply valid percentages and throw for invalid ones")
    void testDiscount() {
        assertEquals(65.0, Calculator.discount(100, 35), 0.001);
        assertEquals(100.0, Calculator.discount(100, 0), 0.001);
        assertEquals(0.0, Calculator.discount(100, 100), 0.001);

        Exception e1 = assertThrows(IllegalArgumentException.class,
                () -> Calculator.discount(100, -10));
        Exception e2 = assertThrows(IllegalArgumentException.class,
                () -> Calculator.discount(100, 150));

        assertEquals("Percentage must be between 0 and 100", e1.getMessage());
        assertEquals("Percentage must be between 0 and 100", e2.getMessage());
    }

    // CalculateTotal
    @Test
    @DisplayName("Calculate total should handle multiple, single and empty lists")
    void testCalculateTotal() {
        assertEquals(60.0, Calculator.calculateTotal(List.of(10.0, 20.0, 30.0)), 0.001);
        assertEquals(42.0, Calculator.calculateTotal(List.of(42.0)), 0.001);
        assertEquals(0.0, Calculator.calculateTotal(new ArrayList<>()), 0.001);
    }

    // Constant
    @Test
    @DisplayName("Constant EMPTY should equal 'empty'")
    void testEmptyConstant() {
        assertEquals("empty", Calculator.EMPTY);
    }

    
}
