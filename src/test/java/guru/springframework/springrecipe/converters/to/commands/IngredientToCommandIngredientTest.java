package guru.springframework.springrecipe.converters.to.commands;

import guru.springframework.springrecipe.commands.IngredientCommand;
import guru.springframework.springrecipe.domain.Ingredient;
import guru.springframework.springrecipe.domain.Recipe;
import guru.springframework.springrecipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToCommandIngredientTest {

    private static final Recipe RECIPE = new Recipe();
    private static final BigDecimal AMOUNT = new BigDecimal("12");
    private static final String DESCRIPTION = "test-description";
    private static final Long UOM_ID = 2L;
    private static final Long ID_VAL = 1L;


    IngredientToCommandIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToCommandIngredient(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullConverter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }



    @Test
    void convert() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VAL);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);
        ingredient.setUnitOfMeasure(unitOfMeasure);

        IngredientCommand command = converter.convert(ingredient);
        assertNotNull(command);
        assertNotNull(command.getUnitOfMeasureCommand());
        assertEquals(UOM_ID, command.getUnitOfMeasureCommand().getId());
        assertEquals(ID_VAL, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
    }

    @Test
    void convertWithOutUnom() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VAL);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);

        IngredientCommand command = converter.convert(ingredient);
        assertNotNull(command);
        assertNull(command.getUnitOfMeasureCommand());
        assertEquals(ID_VAL, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
    }
}