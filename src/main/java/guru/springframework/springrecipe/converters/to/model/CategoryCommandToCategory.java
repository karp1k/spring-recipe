package guru.springframework.springrecipe.converters.to.model;

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
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    // see: https://projectlombok.org/features/Synchronized
    @Synchronized // locks on generated field $lock and on static field $LOCK if method is static
    @Nullable // Methods override should repeat parent @Nullabnle annotations unless they behave differently
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if (categoryCommand == null) {
            return null;
        }
        final Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setDescription(categoryCommand.getDescription());
        return category;
    }
}
