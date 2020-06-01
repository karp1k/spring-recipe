package guru.springframework.springrecipe.converters.to.commands;

import guru.springframework.springrecipe.commands.NotesCommand;
import guru.springframework.springrecipe.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    private static final Long ID = 1L;

    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void testNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmpty() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {
        Notes notes = new Notes();
        notes.setId(ID);

        NotesCommand command = converter.convert(notes);
        assertNotNull(command);
        assertEquals(ID, command.getId());

    }
}