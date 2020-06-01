package guru.springframework.springrecipe.converters;

import guru.springframework.springrecipe.commands.NotesCommand;
import guru.springframework.springrecipe.converters.to.model.NotesCommandToNotes;
import guru.springframework.springrecipe.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    private static final Long ID = 1L;
    private static final String NOTES_RECIPE = "Some test notes for recipe";
    private NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void testNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmpty() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setRecipeNotes(NOTES_RECIPE);

        Notes notes = converter.convert(notesCommand);

        assertNotNull(notes);
        assertEquals(ID, notes.getId());
        assertEquals(NOTES_RECIPE, notes.getRecipeNotes());
    }
}