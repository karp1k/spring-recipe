package guru.springframework.springrecipe.repositories;

import guru.springframework.springrecipe.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

/**
 * @author kas
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
