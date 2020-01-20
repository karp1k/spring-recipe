package guru.springframework.springrecipe.bootstrap;

import guru.springframework.springrecipe.domain.*;
import guru.springframework.springrecipe.repositories.CategoryRepository;
import guru.springframework.springrecipe.repositories.IngredientRepository;
import guru.springframework.springrecipe.repositories.RecipeRepository;
import guru.springframework.springrecipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author kas
 */
@Slf4j
@Service
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientRepository ingredientRepository;

    public DataLoader(CategoryRepository categoryRepository,
                      RecipeRepository recipeRepository,
                      UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("ContextRefreshed Event: bootstrap data!");
        Category mexicanCategory = categoryRepository.findByDescription("mexican").get();
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("teaspoon").get();
        UnitOfMeasure tableSpoon = unitOfMeasureRepository.findByDescription("tablespoon").get();



        Recipe grilledChickenTacos = new Recipe();
        grilledChickenTacos.setDescription("Spicy Grilled Chicken Tacos");
        grilledChickenTacos.getCategorySet().add(mexicanCategory);
        grilledChickenTacos.setPrepTime(20);
        grilledChickenTacos.setServings(6);
        grilledChickenTacos.setCookTime(15);
        grilledChickenTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. " +
                "I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.");
        grilledChickenTacos.setNotes(tacoNotes);
        grilledChickenTacos = recipeRepository.save(grilledChickenTacos);

        Recipe perfectGuacamoleRecipe = new Recipe();
        perfectGuacamoleRecipe.setDescription("How to Make Perfect Guacamole Recipe");
        perfectGuacamoleRecipe.setPrepTime(10);
        perfectGuacamoleRecipe.setServings(4);
        perfectGuacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamoleRecipe.setCategorySet(Collections.singleton(mexicanCategory));

        Notes guacamolNotes = new Notes();
        guacamolNotes.setRecipeNotes("Guacamole! Did you know that over 2 billion pounds of avocados are consumed each year in the U.S.? (Google it.) " +
                "That’s over 7 pounds per person. I’m guessing that most of those avocados go into what has become America’s favorite dip, guacamole.\n" +
                "Where Does Guacamole Come From?\n" +
                "\n" +
                "The word “guacamole”, and the dip, are both originally from Mexico, where avocados have been cultivated for thousands of years. " +
                "The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).");
        perfectGuacamoleRecipe.setNotes(guacamolNotes);

        perfectGuacamoleRecipe = recipeRepository.save(perfectGuacamoleRecipe);

        Ingredient ripeAvocados = new Ingredient();
        ripeAvocados.setAmount(BigDecimal.valueOf(2));
        ripeAvocados.setDescription("ripe avocados");
        ripeAvocados.setRecipe(perfectGuacamoleRecipe);
        ripeAvocados = ingredientRepository.save(ripeAvocados);

        Ingredient quarterOfSalt = new Ingredient();
        quarterOfSalt.setDescription("1/4 teaspoon of salt, more to taste");
        quarterOfSalt.setAmount(BigDecimal.valueOf(0.25));
        quarterOfSalt.setUnitOfMeasure(teaspoon);
        quarterOfSalt.setRecipe(perfectGuacamoleRecipe);
        quarterOfSalt = ingredientRepository.save(quarterOfSalt);

        Ingredient oneTableSpoonOfLimeJuice = new Ingredient();
        oneTableSpoonOfLimeJuice.setDescription("1 tablespoo fresh lime juice or lemon juice");
        oneTableSpoonOfLimeJuice.setAmount(BigDecimal.valueOf(1));
        oneTableSpoonOfLimeJuice.setUnitOfMeasure(tableSpoon);
        oneTableSpoonOfLimeJuice.setRecipe(perfectGuacamoleRecipe);
        oneTableSpoonOfLimeJuice = ingredientRepository.save(oneTableSpoonOfLimeJuice);

        Ingredient twoTableSpoonsOfMincedRedOnion = new Ingredient();
        twoTableSpoonsOfMincedRedOnion.setDescription("2 tablespoons to 1/4 of minced red onion");
        twoTableSpoonsOfMincedRedOnion.setAmount(BigDecimal.valueOf(2));
        twoTableSpoonsOfMincedRedOnion.setUnitOfMeasure(tableSpoon);
        twoTableSpoonsOfMincedRedOnion.setRecipe(perfectGuacamoleRecipe);
        twoTableSpoonsOfMincedRedOnion = ingredientRepository.save(twoTableSpoonsOfMincedRedOnion);

        Ingredient serranoChiles = new Ingredient();
        serranoChiles.setDescription("1 -2 serrano chiles, stems and seeds removed, minced");
        serranoChiles.setAmount(BigDecimal.valueOf(2));
        serranoChiles.setRecipe(perfectGuacamoleRecipe);
        serranoChiles = ingredientRepository.save(serranoChiles);

        Ingredient cilantro = new Ingredient();
        cilantro.setDescription("2 tablespoons cilantro (leaves and tender stems), finely chopped");
        cilantro.setAmount(BigDecimal.valueOf(2));
        cilantro.setUnitOfMeasure(tableSpoon);
        cilantro.setRecipe(perfectGuacamoleRecipe);
        cilantro = ingredientRepository.save(cilantro);

        Ingredient blackPepper = new Ingredient();
        blackPepper.setDescription("A dash of freshly grated black pepper");
        blackPepper.setAmount(BigDecimal.valueOf(1));
        blackPepper.setRecipe(perfectGuacamoleRecipe);
        blackPepper = ingredientRepository.save(blackPepper);

        Ingredient anchoChiliPowder = new Ingredient();
        anchoChiliPowder.setDescription("2 tablespoons ancho chili powder");
        anchoChiliPowder.setRecipe(grilledChickenTacos);
        anchoChiliPowder.setAmount(BigDecimal.valueOf(2));
        anchoChiliPowder.setUnitOfMeasure(tableSpoon);
        anchoChiliPowder = ingredientRepository.save(anchoChiliPowder);

        Ingredient driedOregano = new Ingredient();
        driedOregano.setDescription("1 teaspoon dried oregano");
        driedOregano.setRecipe(grilledChickenTacos);
        driedOregano.setAmount(BigDecimal.valueOf(1));
        driedOregano.setUnitOfMeasure(teaspoon);
        driedOregano = ingredientRepository.save(driedOregano);

        Ingredient driedCumin = new Ingredient();
        driedCumin.setRecipe(grilledChickenTacos);
        driedCumin.setDescription("1 teaspoon dried cumin");
        driedCumin.setAmount(BigDecimal.valueOf(1));
        driedCumin.setUnitOfMeasure(teaspoon);
        driedCumin = ingredientRepository.save(driedCumin);

        Ingredient sugar = new Ingredient();
        sugar.setRecipe(grilledChickenTacos);
        sugar.setDescription("1 teaspoon sugar");
        sugar.setAmount(BigDecimal.valueOf(1));
        sugar.setUnitOfMeasure(teaspoon);
        sugar = ingredientRepository.save(sugar);
    }
}
