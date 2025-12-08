import com.example.model.Article;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArticleTestCase {

    private Article article;

    @BeforeEach
    void setUp() {
        article = new Article("Libro", 2, 10.0, 20.0);
    }

    @Test
    @DisplayName("Gross amount should be quantity × price")
    void testGetGrossAmount() {
        double result = article.getGrossAmount();
        assertEquals(20.0, result, 0.001);
    }

    @Test
    @DisplayName("Discounted amount should apply percentage correctly")
    void testGetDiscountedAmount() {
        double result = article.getDiscountedAmount();
        assertEquals(16.0, result, 0.001);
    }

    @Test
    @DisplayName("Discounted amount with 0% discount should equal gross amount")
    void testNoDiscount() {
        Article a = new Article("Bolígrafo", 5, 2.0, 0.0);
        assertEquals(10.0, a.getDiscountedAmount(), 0.001);
    }

    @Test
    @DisplayName("Discounted amount with 100% discount should be 0")
    void testFullDiscount() {
        Article a = new Article("Camiseta", 1, 30.0, 100.0);
        assertEquals(0.0, a.getDiscountedAmount(), 0.001);
    }

    @Test
    @DisplayName("toString should contain article name and price")
    void testToString() {
        String text = article.toString();
        assertTrue(text.contains("Libro"));
        assertTrue(text.contains("10.0"));
    }

    @Test
    @DisplayName("Discount with invalid percentage should throw exception")
    void testInvalidDiscount() {
        Article a = new Article("ErrorItem", 1, 10.0, -5.0);
        assertThrows(IllegalArgumentException.class, a::getDiscountedAmount);
    }

    @Test
    @DisplayName("Constructor and getters should return correct values")
    void testConstructorAndGetters() {
        Article a = new Article("Cuaderno", 3, 5.5, 10.0);
        assertEquals("Cuaderno", a.getNombre());
        assertEquals(3, a.getCantidad());
        assertEquals(5.5, a.getPrecio());
        assertEquals(10.0, a.getDescuento());
    }

    @Test
    @DisplayName("Setters should correctly update attribute values")
    void testSetters() {
        Article a = new Article("Lápiz", 1, 1.0, 0.0);
        a.setNombre("Goma");
        a.setCantidad(4);
        a.setPrecio(2.5);
        a.setDescuento(5.0);

        assertEquals("Goma", a.getNombre());
        assertEquals(4, a.getCantidad());
        assertEquals(2.5, a.getPrecio());
        assertEquals(5.0, a.getDescuento());
    }

}
