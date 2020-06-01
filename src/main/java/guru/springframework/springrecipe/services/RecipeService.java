package guru.springframework.springrecipe.services;

import guru.springframework.springrecipe.commands.RecipeCommand;
import guru.springframework.springrecipe.domain.Recipe;

import java.util.Set;

/**
 * @author kas
 */
public interface RecipeService {

    Set<Recipe> getAllRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long id);

    void deleteById(Long id);
}
