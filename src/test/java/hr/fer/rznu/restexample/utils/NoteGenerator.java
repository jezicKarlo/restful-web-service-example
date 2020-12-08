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
        List<Note> notes = new ArrayList<>();
        notes.add(kjezicNoteToSave());
        return notes;
    }

    public Note kjezicNoteToSave() {
        Note note = new Note();
        note.setId(1);
        note.setUser(UserGenerator.createKjezic());
        note.setName("name");
        note.setContent("content");
        return note;
    }

    public NoteBody kjezicNoteBody() {
        NoteBody noteBody = new NoteBody();
        Note note = kjezicNoteToSave();
        noteBody.setName(note.getName());
        noteBody.setContent(note.getContent());
        return noteBody;
    }
}
