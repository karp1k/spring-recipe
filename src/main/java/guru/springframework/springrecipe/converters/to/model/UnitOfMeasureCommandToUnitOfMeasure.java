package guru.springframework.springrecipe.converters.to.model;

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
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    // see: https://projectlombok.org/features/Synchronized
    @Synchronized // locks on generated field $lock and on static field $LOCK if method is static
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if (unitOfMeasureCommand == null) {
            return null;
        }
        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(unitOfMeasureCommand.getId());
        unitOfMeasure.setDescription(unitOfMeasureCommand.getDescription());
        return unitOfMeasure;
    }
}
