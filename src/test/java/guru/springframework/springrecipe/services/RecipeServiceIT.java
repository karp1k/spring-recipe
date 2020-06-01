package guru.springframework.springrecipe.services;

import guru.springframework.springrecipe.commands.RecipeCommand;
import guru.springframework.springrecipe.converters.to.commands.RecipeToRecipeCommand;
import guru.springframework.springrecipe.converters.to.model.RecipeCommandToRecipe;
import guru.springframework.springrecipe.domain.Recipe;
import guru.springframework.springrecipe.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author kas
 */
@SpringBootTest
public class RecipeServiceIT {

    private static final String DESCRIPTION = "test_description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;


    public void testSaveOfDesciption() {
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().hasNext() ? recipes.iterator().next() : null;
        assertNotNull(testRecipe);

        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);
        testRecipeCommand.setDescription(DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        assertEquals(DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategorySet().size(), savedRecipeCommand.getCategoryCommands().size());
        assertEquals(testRecipe.getIngredientSet().size(), savedRecipeCommand.getIngredientCommands().size());
    }
}
