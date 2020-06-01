package guru.springframework.springrecipe.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kas
 */
@Data
@Entity
public class Recipe {

    @Id
    // GennerationType.IDENTITY supported by MySql, Oracle 12, H2...
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    // EnumType.ORDINAL persist into db id of enum values
    // EnumType.STRING persist into db names (values) of enum (prefer)
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredientSet = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categorySet = new HashSet<>();


    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredientSet.add(ingredient);
        return this;
    }
}
