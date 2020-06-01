package guru.springframework.springrecipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author kas
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private BigDecimal amount;
    private String description;
    private UnitOfMeasureCommand unitOfMeasureCommand;

}
