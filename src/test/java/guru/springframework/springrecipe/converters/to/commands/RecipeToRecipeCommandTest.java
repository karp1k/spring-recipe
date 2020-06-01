package guru.springframework.springrecipe.converters.to.commands;

import guru.springframework.springrecipe.commands.RecipeCommand;
import guru.springframework.springrecipe.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

    private static final Long RECIPE_ID = 1L;
    private static final Integer COOK_TIME = 5;
    private static final Integer PREP_TIME = 12;
    private static final String DESC = "Description";
    private static final String DIRECTION = "direction";
    private static final Difficulty DIFF = Difficulty.EASY;
    private static final Integer SERVING = 3;
    private static final String SOURCE = "source";
    private static final String URL = "url";

    private static final Long NOTES_ID = 2L;
    private static final Long INGREDIENT_1_ID = 3L;
    private static final Long CATEGORY_1_ID = 4L;

    RecipeToRecipeCommand converter;


    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(
                new IngredientToCommandIngredient(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCommandCategory(),
                new NotesToNotesCommand());
    }

    @Test
    void testNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmpty() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() {
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setUrl(URL);
        recipe.setSource(SOURCE);
        recipe.setServings(SERVING);
        recipe.setDifficulty(DIFF);
        recipe.setDirections(DIRECTION);
        recipe.setDescription(DESC);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_1_ID);

        recipe.getIngredientSet().add(ingredient);

        Category category = new Category();
        category.setId(CATEGORY_1_ID);

        recipe.getCategorySet().add(category);

        RecipeCommand command = converter.convert(recipe);
        assertNotNull(command);
        assertNotNull(command.getNotesCommand());
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(URL, command.getUrl());
        assertEquals(SOURCE, command.getSource());
        assertEquals(SERVING, command.getServings());
        assertEquals(DIFF, command.getDifficulty());
        assertEquals(DIRECTION, command.getDirections());
        assertEquals(DESC, command.getDescription());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
    }


}