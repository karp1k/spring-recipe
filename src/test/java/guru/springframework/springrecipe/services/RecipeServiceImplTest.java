package guru.springframework.springrecipe.services;

import guru.springframework.springrecipe.converters.to.commands.RecipeToRecipeCommand;
import guru.springframework.springrecipe.converters.to.model.RecipeCommandToRecipe;
import guru.springframework.springrecipe.domain.Recipe;
import guru.springframework.springrecipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getAllRecipes() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);
        Mockito.when(recipeRepository.findAll()).thenReturn(recipeData);
        Set<Recipe> recipeSet = recipeService.getAllRecipes();
        assertEquals(1, recipeSet.size());
        // check how many times Mock method was called
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        Long id = 2L;
        recipeRepository.deleteById(id);
        Mockito.verify(recipeRepository, Mockito.times(1)).deleteById(anyLong());
    }
}