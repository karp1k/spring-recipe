package guru.springframework.springrecipe.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author kas
 */
@Data
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
