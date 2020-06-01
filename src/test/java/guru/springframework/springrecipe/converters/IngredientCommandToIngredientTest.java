package guru.springframework.springrecipe.converters;

import guru.springframework.springrecipe.commands.IngredientCommand;
import guru.springframework.springrecipe.commands.UnitOfMeasureCommand;
import guru.springframework.springrecipe.converters.to.model.IngredientCommandToIngredient;
import guru.springframework.springrecipe.converters.to.model.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.springrecipe.domain.Ingredient;
import guru.springframework.springrecipe.domain.Recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final String DESCRIPTION = "Test Ingredient";
    private static final Long ID = 1L;
    private static final Long UOM_ID = 2L;
    private static final Recipe RECIPE = new Recipe();
    private IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void testOnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmpty() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        IngredientCommand command = new IngredientCommand();
        command.setId(ID);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        command.setUnitOfMeasureCommand(unitOfMeasureCommand);

        Ingredient ingredient = converter.convert(command);
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    void convertWithNullUnom() {
        IngredientCommand command = new IngredientCommand();
        command.setId(ID);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);

        Ingredient ingredient = converter.convert(command);
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());


    }

}