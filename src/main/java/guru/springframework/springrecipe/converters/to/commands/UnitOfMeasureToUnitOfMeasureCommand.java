package guru.springframework.springrecipe.converters.to.commands;

import guru.springframework.springrecipe.commands.UnitOfMeasureCommand;
import guru.springframework.springrecipe.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author kas
 */
@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    // see: https://projectlombok.org/features/Synchronized
    @Synchronized // locks on generated field $lock and on static field $LOCK if method is static
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null) {
            return null;
        }
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(unitOfMeasure.getId());
        command.setDescription(unitOfMeasure.getDescription());
        return command;
    }
}
