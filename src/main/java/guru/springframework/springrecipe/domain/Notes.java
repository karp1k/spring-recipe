package guru.springframework.springrecipe.domain;

import javax.persistence.*;

/**
 * @author kas
 */
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
