package guru.springframework.springrecipe.converters.to.commands;

import guru.springframework.springrecipe.commands.CategoryCommand;
import guru.springframework.springrecipe.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author kas
 */
@Component
public class CategoryToCommandCategory implements Converter<Category, CategoryCommand> {

    // see: https://projectlombok.org/features/Synchronized
    @Synchronized // locks on generated field $lock and on static field $LOCK if method is static
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        if (category == null) {
            return null;
        }
        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setDescription(category.getDescription());
        return categoryCommand;
    }
}
