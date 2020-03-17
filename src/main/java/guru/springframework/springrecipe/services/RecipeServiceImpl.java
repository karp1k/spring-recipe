package guru.springframework.springrecipe.services;

import guru.springframework.springrecipe.domain.Recipe;
import guru.springframework.springrecipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author kas
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public Set<Recipe> getAllRecipes() {
        log.debug("getAllRecipes method");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOp = recipeRepository.findById(id);
        if (!recipeOp.isPresent()) {
            throw new RuntimeException("Did not found the Recipe");
        }
        return recipeOp.get();
    }
}
