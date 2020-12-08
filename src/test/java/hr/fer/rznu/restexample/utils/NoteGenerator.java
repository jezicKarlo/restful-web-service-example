package hr.fer.rznu.restexample.utils;

import hr.fer.rznu.restexample.dto.NoteBody;
import hr.fer.rznu.restexample.entity.Note;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class NoteGenerator {

    private final int KEJZIC_NOTE_ID = 1;

    public List<Note> kjezicNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(kjezicNote());
        return notes;
    }

    public Note kjezicNote() {
        Note note = new Note();
        note.setId(KEJZIC_NOTE_ID);
        note.setUser(UserGenerator.createKjezic());
        note.setName("name");
        note.setContent("content");
        return note;
    }

    public NoteBody kjezicNoteBody() {
        NoteBody noteBody = new NoteBody();
        Note note = kjezicNote();
        noteBody.setName(note.getName());
        noteBody.setContent(note.getContent());
        return noteBody;
    }

    public Note kjezicEditedNote() {
        Note noteToEdit = kjezicNote();
        noteToEdit.setContent("CONTENT");
        noteToEdit.setName("NAME");
        return noteToEdit;
    }

    public NoteBody editNote() {
        NoteBody noteBody = new NoteBody();
        noteBody.setContent("CONTENT");
        noteBody.setName("NAME");
        return noteBody;
    }

    public static int getKejzicNoteId() {
        return KEJZIC_NOTE_ID;
    }
}
