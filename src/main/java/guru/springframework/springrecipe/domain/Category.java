package guru.springframework.springrecipe.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kas
 */
@Data
@EqualsAndHashCode(exclude = {"recipeSet"})
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToMany(mappedBy = "categorySet")
    private Set<Recipe> recipeSet = new HashSet<>();


}
