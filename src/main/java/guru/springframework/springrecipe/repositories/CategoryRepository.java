package guru.springframework.springrecipe.repositories;

import guru.springframework.springrecipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * @author kas
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
