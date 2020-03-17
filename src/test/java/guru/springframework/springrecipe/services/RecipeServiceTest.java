package guru.springframework.springrecipe.services;

import guru.springframework.springrecipe.domain.Recipe;
import guru.springframework.springrecipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceTest {

    private RecipeServiceImpl recipeService;

    private final Long id = 1L;

    @Mock
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipeByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(id);
        Optional<Recipe> recipeOp = Optional.of(recipe);
        when(recipeRepository.findById(any())).thenReturn(recipeOp);
        Recipe returnedRecipe = recipeService.findById(id);
        assertNotNull("Null recipe has returned", returnedRecipe);
        verify(recipeRepository).findById(id);
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getAllRecipes() {
    }
}