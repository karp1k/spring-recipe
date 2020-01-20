package guru.springframework.springrecipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author kas
 */
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String recipeNotes;

    @OneToOne
    private Recipe recipe;
}
