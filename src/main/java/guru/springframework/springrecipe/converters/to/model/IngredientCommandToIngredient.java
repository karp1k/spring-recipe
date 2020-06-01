package guru.springframework.springrecipe.converters.to.model;

import guru.springframework.springrecipe.commands.IngredientCommand;
import guru.springframework.springrecipe.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author kas
 */
@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
    }

    // see: https://projectlombok.org/features/Synchronized
    @Synchronized // locks on generated field $lock and on static field $LOCK if method is static
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if (ingredientCommand == null) {
            return null;
        }
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientCommand.getId());
        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setUnitOfMeasure(unitOfMeasureCommandToUnitOfMeasure
                .convert(ingredientCommand.getUnitOfMeasureCommand()));
        ingredient.setDescription(ingredientCommand.getDescription());
        return ingredient;
    }
}
