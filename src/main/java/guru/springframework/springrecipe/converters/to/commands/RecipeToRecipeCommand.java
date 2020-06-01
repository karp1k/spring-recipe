package guru.springframework.springrecipe.converters.to.commands;

import guru.springframework.springrecipe.commands.RecipeCommand;
import guru.springframework.springrecipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author kas
 */
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToCommandIngredient ingredientToCommandIngredient;
    private final CategoryToCommandCategory categoryToCommandCategory;
    private final NotesToNotesCommand notesToNotesCommand;

    public RecipeToRecipeCommand(IngredientToCommandIngredient ingredientToCommandIngredient,
                                 CategoryToCommandCategory categoryToCommandCategory,
                                 NotesToNotesCommand notesToNotesCommand) {
        this.ingredientToCommandIngredient = ingredientToCommandIngredient;
        this.categoryToCommandCategory = categoryToCommandCategory;
        this.notesToNotesCommand = notesToNotesCommand;
    }

    // see: https://projectlombok.org/features/Synchronized
    @Synchronized // locks on generated field $lock and on static field $LOCK if method is static
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setNotesCommand(notesToNotesCommand.convert(recipe.getNotes()));

        if (recipe.getCategorySet().size() > 0) {
            recipe.getCategorySet()
                    .forEach(category -> recipeCommand.getCategoryCommands()
                            .add(categoryToCommandCategory.convert(category)));
        }

        if (recipe.getIngredientSet().size() > 0) {
            recipe.getIngredientSet()
                    .forEach(ingredient -> recipeCommand.getIngredientCommands()
                            .add(ingredientToCommandIngredient.convert(ingredient)));
        }

        return recipeCommand;
    }
}
