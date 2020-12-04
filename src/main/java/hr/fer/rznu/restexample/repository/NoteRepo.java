package hr.fer.rznu.restexample.repository;

import hr.fer.rznu.restexample.entity.Note;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NoteRepo implements NoteRepository {

    private Map<Integer, Note> notes;

    @Override
    public Note getNote(Integer id) {
        return notes.get(id);
    }

    @Override
    public Note deleteNote(Integer id) {
        return notes.remove(id);
    }

    @Override
    public void crateNote(Note note) {
        notes.put(note.getId(), note);
    }

    @Override
    public Note editNote(Note note) {
        return notes.replace(note.getId(), note);
    }
}
