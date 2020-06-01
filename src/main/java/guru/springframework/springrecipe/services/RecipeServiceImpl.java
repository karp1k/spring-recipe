package guru.springframework.springrecipe.services;

import guru.springframework.springrecipe.commands.RecipeCommand;
import guru.springframework.springrecipe.converters.to.commands.RecipeToRecipeCommand;
import guru.springframework.springrecipe.converters.to.model.RecipeCommandToRecipe;
import guru.springframework.springrecipe.domain.Recipe;
import guru.springframework.springrecipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author kas
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final Converter<RecipeCommand, Recipe> recipeCommandToRecipe;
    private final Converter<Recipe, RecipeCommand> recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }


    @Override
    public Set<Recipe> getAllRecipes() {
        log.debug("getAllRecipes method");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOp = recipeRepository.findById(id);
        if (!recipeOp.isPresent()) {
            throw new RuntimeException("Did not found the Recipe");
        }
        return recipeOp.get();
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {

        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved recipe with id {}", savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
