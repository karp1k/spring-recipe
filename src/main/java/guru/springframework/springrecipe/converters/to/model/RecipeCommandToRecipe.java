package guru.springframework.springrecipe.converters.to.model;

import guru.springframework.springrecipe.commands.RecipeCommand;
import guru.springframework.springrecipe.converters.to.model.CategoryCommandToCategory;
import guru.springframework.springrecipe.converters.to.model.IngredientCommandToIngredient;
import guru.springframework.springrecipe.converters.to.model.NotesCommandToNotes;
import guru.springframework.springrecipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author kas
 */
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final NotesCommandToNotes notesCommandToNotes;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandToIngredient,
                                 CategoryCommandToCategory categoryCommandToCategory,
                                 NotesCommandToNotes notesCommandToNotes) {
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.notesCommandToNotes = notesCommandToNotes;
    }

    // see: https://projectlombok.org/features/Synchronized
    @Synchronized // locks on generated field $lock and on static field $LOCK if method is static
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setNotes(notesCommandToNotes.convert(recipeCommand.getNotesCommand()));
        if (recipeCommand.getCategoryCommands().size() > 0) {
            recipeCommand.getCategoryCommands()
                    .forEach(categoryC -> recipe.getCategorySet()
                            .add(categoryCommandToCategory
                                    .convert(categoryC)));
        }
        if (recipeCommand.getIngredientCommands().size() > 0) {
            recipeCommand.getIngredientCommands()
                    .forEach(ingredientC -> recipe.getIngredientSet()
                            .add(ingredientCommandToIngredient
                                    .convert(ingredientC)));
        }
        return recipe;
    }
}
