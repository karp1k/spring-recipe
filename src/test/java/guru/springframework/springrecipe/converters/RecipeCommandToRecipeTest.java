package guru.springframework.springrecipe.converters;

import guru.springframework.springrecipe.commands.CategoryCommand;
import guru.springframework.springrecipe.commands.IngredientCommand;
import guru.springframework.springrecipe.commands.NotesCommand;
import guru.springframework.springrecipe.commands.RecipeCommand;
import guru.springframework.springrecipe.converters.to.model.*;
import guru.springframework.springrecipe.domain.Difficulty;
import guru.springframework.springrecipe.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    private static final Long RECIPE_ID = 1L;
    private static final Integer COOCK_TIME = 5;
    private static final Integer PREP_TIME = 5;
    private static final String RECIPE_DESCRIPTION = "Test recipe description";
    private static final String DIRECTIONS = "directions";
    private static final String SOURCE = "localhost:8080";
    private static final String URL = "http://ya.ru";
    private static final Difficulty DIFFICULTY = Difficulty.HARD;
    private static final Integer SERVINGS = 4;
    private static final Long NOTE_ID = 2L;
    private static final Long CATEGORY_1_ID = 4L;
    private static final Long CATEGORY_2_ID = 5L;
    private static final Long INGREDIENT_ID = 3L;

    private RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new CategoryCommandToCategory(),
                new NotesCommandToNotes());
    }

    @Test
    void testNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmpty() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOCK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDescription(RECIPE_DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTE_ID);
        recipeCommand.setNotesCommand(notesCommand);
        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CATEGORY_1_ID);
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CATEGORY_2_ID);
        recipeCommand.getCategoryCommands()
                .addAll(Arrays.asList(categoryCommand1, categoryCommand2));
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);
        recipeCommand.getIngredientCommands().add(ingredientCommand);

        Recipe recipe = converter.convert(recipeCommand);
        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOCK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(2, recipe.getCategorySet().size());
        assertEquals(1, recipe.getIngredientSet().size());
        assertNotNull(recipe.getNotes());
        assertEquals(NOTE_ID, recipe.getNotes().getId());
    }
}