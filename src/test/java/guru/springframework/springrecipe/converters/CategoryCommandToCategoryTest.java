package guru.springframework.springrecipe.converters;

import guru.springframework.springrecipe.commands.CategoryCommand;
import guru.springframework.springrecipe.converters.to.model.CategoryCommandToCategory;
import guru.springframework.springrecipe.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "Test description";

    private CategoryCommandToCategory categoryCommandToCategory;

    @BeforeEach
    void setUp() {
        categoryCommandToCategory = new CategoryCommandToCategory();
    }

    @Test
    void testNull() {
        assertNull(categoryCommandToCategory.convert(null));
    }

    @Test
    void testEmpty() {
        assertNotNull(categoryCommandToCategory.convert(new CategoryCommand()));

    }

    @Test
    void convert() {
        CategoryCommand command = new CategoryCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        Category category = categoryCommandToCategory.convert(command);
        assertNotNull(category);
        assertEquals(ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}