package guru.springframework.springrecipe.converters.to.commands;

import guru.springframework.springrecipe.commands.NotesCommand;
import guru.springframework.springrecipe.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author kas
 */
@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    // see: https://projectlombok.org/features/Synchronized
    @Synchronized // locks on generated field $lock and on static field $LOCK if method is static
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {
        if (notes == null) {
            return null;
        }
        final NotesCommand command = new NotesCommand();
        command.setId(notes.getId());
        command.setRecipeNotes(notes.getRecipeNotes());
        return command;
    }
}
