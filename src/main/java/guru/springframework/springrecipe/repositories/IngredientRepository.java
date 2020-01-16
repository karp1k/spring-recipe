package guru.springframework.springrecipe.repositories;

import guru.springframework.springrecipe.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * @author kas
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
