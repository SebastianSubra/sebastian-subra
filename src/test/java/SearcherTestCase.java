import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Searcher;

public class SearcherTestCase{

    Searcher searcher;

    @BeforeEach
    void setUp(){
        searcher = new Searcher();
    }

    @Test
    @DisplayName("Check exact phrase search")
    void testSearchExactPhrase(){
        boolean check = searcher.searchExactPhrase("Adios", List.of("Hola", "Adios", "Hello"));
        assertTrue(check);
    }

    @Test
    @DisplayName("Check exact phrase search when phrase does not exist")
    void testSearchExactPhraseNotFound(){
        boolean check = searcher.searchExactPhrase("Bonjour", List.of("Hola", "Adios", "Hello"));
        assertFalse(check);
    }

    @Test
    @DisplayName("Check word search")
    void testSearchWord(){
        boolean check = searcher.searchWord("Hello", List.of("Hola", "Adios", "Hello"));
        assertTrue(check);
    }

    @Test
    @DisplayName("Check word search when word does not exist")
    void testSearchWordNotFound(){
        boolean check = searcher.searchWord("Bonjour", List.of("Hola", "Adios", "Hello"));
        assertFalse(check);
    }

    @Test
    @DisplayName("Check get word by index")
    void testGetWordByIndex(){
        String word = searcher.getWordByIndex(List.of("Hola", "Adios", "Hello"),2);
        assertEquals("Hello", word);
    }

    @Test
    @DisplayName("Check get word by index out of bounds")
    void testGetWordByIndexOutOfBounds(){
        String word = searcher.getWordByIndex(List.of("Hola", "Adios", "Hello"),-1);
        assertNull(word);  
        
        String word2 = searcher.getWordByIndex(List.of("Hola", "Adios", "Hello"),8);
        assertNull(word2);
    }

    @Test
    @DisplayName("Check search by prefix")
    void testSearchByPrefix(){
        List<String> results = searcher.searchByPrefix("He", List.of("Hell", "Hello", "Hola", "Heressy"));
        assertEquals(results, List.of("Hell", "Hello", "Heressy"));
    }

    @Test
    @DisplayName("Check search by prefix")
    void wrongTestSearchByPrefix(){
        List<String> results = searcher.searchByPrefix("Op", List.of("Hell", "Hello", "Hola", "Heressy"));
        assertEquals(results, List.of());
    }

    @Test
    @DisplayName("Check filter by keyword")
    void testFilterByKeyword(){
        List<String> results = searcher.filterByKeyword("ere", List.of("Hell", "Hello", "Hola", "Heressy"));
        assertEquals(results, List.of("Heressy"));
    }

    @Test
    @DisplayName("Check filter by keyword with no matches")
    void wrongTestFilterByKeyword(){
        List<String> results = searcher.filterByKeyword("xyz", List.of("Hell", "Hello", "Hola", "Heressy"));
        assertEquals(results, List.of());
    }

}
