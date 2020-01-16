package guru.springframework.springrecipe.services;

import guru.springframework.springrecipe.domain.Recipe;

import java.util.Set;

/**
 * @author kas
 */
public interface RecipeService {

    Set<Recipe> getAllRecipes();
}
