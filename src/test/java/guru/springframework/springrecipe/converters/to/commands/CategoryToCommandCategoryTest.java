package guru.springframework.springrecipe.converters.to.commands;

import guru.springframework.springrecipe.commands.CategoryCommand;
import guru.springframework.springrecipe.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CategoryToCommandCategoryTest {
    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";

    CategoryToCommandCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCommandCategory();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {
        // given
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        // when
        CategoryCommand categoryCommand = converter.convert(category);
        assertNotNull(category);
        assertEquals(ID, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}