package guru.springframework.springrecipe.controllers;

import guru.springframework.springrecipe.domain.Category;
import guru.springframework.springrecipe.domain.UnitOfMeasure;
import guru.springframework.springrecipe.repositories.CategoryRepository;
import guru.springframework.springrecipe.repositories.UnitOfMeasureRepository;
import guru.springframework.springrecipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

/**
 * @author kas
 */
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(
            RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "index";
    }
}
