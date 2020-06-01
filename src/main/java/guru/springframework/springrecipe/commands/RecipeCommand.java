package guru.springframework.springrecipe.commands;

import guru.springframework.springrecipe.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author kas
 */
@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private NotesCommand notesCommand;
    private Set<IngredientCommand> ingredientCommands = new HashSet<>();
    private Set<CategoryCommand> categoryCommands = new HashSet<>();


}
