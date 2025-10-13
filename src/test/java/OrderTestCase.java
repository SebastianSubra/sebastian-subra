import com.example.model.Article;
import com.example.model.Order;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTestCase {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order("ORD001");
        order.addArticle(new Article("Libro", 2, 10.0, 0.0));      // 20.0
        order.addArticle(new Article("Camiseta", 3, 15.0, 10.0));  // 45.0 bruto
    }

    @Test
    @DisplayName("Gross total should sum all article gross amounts")
    void testGetGrossTotal() {
        assertEquals(65.0, order.getGrossTotal(), 0.001);
    }

    @Test
    @DisplayName("Discounted total should sum all discounted article totals")
    void testGetDiscountedTotal() {
        assertEquals(60.5, order.getDiscountedTotal(), 0.001);
    }

    @Test
    @DisplayName("Empty order discounted total should be 0")
    void testEmptyOrderDiscountedTotal() {
        Order emptyOrder = new Order("ORD002");
        assertEquals(0.0, emptyOrder.getDiscountedTotal(), 0.001);
    }

    @Test
    @DisplayName("toString should include order ID and article names")
    void testToString() {
        String text = order.toString();
        assertTrue(text.contains("ORD001"));
        assertTrue(text.contains("Libro"));
        assertTrue(text.contains("Camiseta"));
    }
}
