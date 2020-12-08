package hr.fer.rznu.restexample.utils;

import hr.fer.rznu.restexample.dto.NoteBody;
import hr.fer.rznu.restexample.entity.Note;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class NoteGenerator {

    public NoteBody noteBody_kjezic() {
        NoteBody body = new NoteBody();
        body.setContent("Content");
        body.setName("name");
        return body;
    }

    public List<Note> kjezicNotes() {
        Note note = new Note(noteBody_kjezic());
        note.setId(1);
        List<Note> notes = new ArrayList<>();
        notes.add(note);
        return notes;
    }
}
