package guru.springframework.springrecipe.controllers;

import guru.springframework.springrecipe.commands.RecipeCommand;
import guru.springframework.springrecipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author kas
 */
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipe-form";

    }

    @GetMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipe-form";
    }

    @PostMapping
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand saveCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + saveCommand.getId() + "/show";
    }
}
