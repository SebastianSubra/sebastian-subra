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
}
