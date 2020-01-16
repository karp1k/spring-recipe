package guru.springframework.springrecipe.repositories;

import guru.springframework.springrecipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author kas
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
