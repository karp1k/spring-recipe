package guru.springframework.springrecipe.converters.to.commands;

import guru.springframework.springrecipe.commands.UnitOfMeasureCommand;
import guru.springframework.springrecipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static final Long ID = 2L;
    private static final String DESC = "description";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmpty() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(ID);
        unitOfMeasure.setDescription(DESC);

        UnitOfMeasureCommand command = converter.convert(unitOfMeasure);
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESC, command.getDescription());
    }
}