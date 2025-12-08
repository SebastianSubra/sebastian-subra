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

    @Test
    @DisplayName("Order with multiple discounted articles should sum correctly")
    void testMultipleDiscountedArticles() {
        Order order = new Order("ORD003");
        order.addArticle(new Article("Item1", 1, 100.0, 10.0)); // 90.0
        order.addArticle(new Article("Item2", 2, 50.0, 0.0));   // 100.0
        assertEquals(190.0, order.getDiscountedTotal(), 0.001);
    }

    @Test
    @DisplayName("Constructor and getters should return correct values")
    void testConstructorAndGetters() {
        Order o = new Order("ORD004");
        assertEquals("ORD004", o.getId());
        assertTrue(o.getArticulos().isEmpty());
    }

    @Test
    @DisplayName("Setters should correctly update ID and article list")
    void testSetters() {
        Order o = new Order("ORD005");

        // Creamos algunos artículos
        Article a1 = new Article("Pantalón", 1, 20.0, 0.0);
        Article a2 = new Article("Zapatos", 2, 30.0, 10.0);
        java.util.List<Article> nuevaLista = java.util.Arrays.asList(a1, a2);

        // Cambiamos valores
        o.setId("ORD006");
        o.setArticulos(nuevaLista);

        // Verificamos cambios
        assertEquals("ORD006", o.getId());
        assertEquals(2, o.getArticulos().size());
        assertEquals("Zapatos", o.getArticulos().get(1).getNombre());
    }

    @Test
    @DisplayName("addArticle should correctly add an article to the list")
    void testAddArticle() {
        Order o = new Order("ORD007");
        assertTrue(o.getArticulos().isEmpty());

        Article a = new Article("Bufanda", 1, 10.0, 0.0);
        o.addArticle(a);

        assertEquals(1, o.getArticulos().size());
        assertEquals("Bufanda", o.getArticulos().get(0).getNombre());
    }


}
