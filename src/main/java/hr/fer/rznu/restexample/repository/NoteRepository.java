package hr.fer.rznu.restexample.repository;

import hr.fer.rznu.restexample.entity.Note;

public interface NoteRepository {

    Note getNote(Integer id);
    Note deleteNote(Integer id);
    void crateNote(Note note);
    Note editNote(Note note);
}
