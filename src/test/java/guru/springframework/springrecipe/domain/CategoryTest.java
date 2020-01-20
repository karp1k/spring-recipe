package guru.springframework.springrecipe.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() {
        Long idVal = 1L;
        category.setId(idVal);
        assertEquals(idVal, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipeSet() {
    }
}