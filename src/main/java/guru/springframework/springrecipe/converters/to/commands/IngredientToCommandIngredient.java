package guru.springframework.springrecipe.converters.to.commands;

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
public class IngredientToCommandIngredient implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public IngredientToCommandIngredient(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    // see: https://projectlombok.org/features/Synchronized
    @Synchronized // locks on generated field $lock and on static field $LOCK if method is static
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient var1) {
        if (var1 == null) {
            return null;
        }
        final IngredientCommand command = new IngredientCommand();
        command.setId(var1.getId());
        command.setAmount(var1.getAmount());
        command.setDescription(var1.getDescription());
        command.setUnitOfMeasureCommand(unitOfMeasureToUnitOfMeasureCommand.convert(var1.getUnitOfMeasure()));
        return command;
    }
}
