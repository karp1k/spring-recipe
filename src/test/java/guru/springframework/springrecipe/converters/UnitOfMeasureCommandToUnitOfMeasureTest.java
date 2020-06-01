package guru.springframework.springrecipe.converters;

import guru.springframework.springrecipe.commands.UnitOfMeasureCommand;
import guru.springframework.springrecipe.converters.to.model.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.springrecipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final String DESCRIPTION = "test description";
    private static final Long ID = 1L;

    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testOnNull() {
        UnitOfMeasure unitOfMeasure = converter.convert(null);
        assertNull(unitOfMeasure);

    }

    @Test
    void testEmptyObject() {
        UnitOfMeasure unitOfMeasure = converter.convert(new UnitOfMeasureCommand());
        assertNotNull(unitOfMeasure);

    }


    @Test
    void convert() {
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID);
        unitOfMeasureCommand.setDescription(DESCRIPTION);

        UnitOfMeasure unitOfMeasure = converter.convert(unitOfMeasureCommand);

        assertNotNull(unitOfMeasure);
        assertEquals(ID, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }
}