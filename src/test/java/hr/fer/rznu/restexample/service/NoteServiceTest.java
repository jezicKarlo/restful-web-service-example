package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.NoteDTO;
import hr.fer.rznu.restexample.entity.Note;
import hr.fer.rznu.restexample.repository.NoteRepository;
import hr.fer.rznu.restexample.repository.UserRepository;
import hr.fer.rznu.restexample.utils.NoteGenerator;
import hr.fer.rznu.restexample.utils.UserGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceTest {

    @Test
    public void getNotesTest() {
        NoteRepository noteRepository = Mockito.mock(NoteRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        RootService rootService = Mockito.mock(RootService.class);

        Mockito.when(noteRepository.getAllByUserId(1)).thenReturn(NoteGenerator.kjezicNotes());
        Mockito.when(rootService.authorize(UserGenerator.getKJEZIC_TOKEN(), 1)).thenReturn(true);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(UserGenerator.createKjezic()));

        NoteService noteService = new NoteService(noteRepository, rootService, userRepository);
        List<NoteDTO> notes = noteService.getNotes(UserGenerator.getKJEZIC_TOKEN(), 1);
        assertEquals(1, notes.size());
    }

    @Test
    public void saveNoteTest() {
        NoteRepository noteRepository = Mockito.mock(NoteRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        RootService rootService = Mockito.mock(RootService.class);

        Note note = NoteGenerator.kjezicNoteToSave();
        Mockito.when(noteRepository.save(Mockito.any())).thenReturn(note);
        Mockito.when(rootService.authorize(UserGenerator.getKJEZIC_TOKEN(), 1)).thenReturn(true);

        NoteService noteService = new NoteService(noteRepository, rootService, userRepository);
        NoteDTO saved = noteService.saveNote(NoteGenerator.noteBody_kjezic(), UserGenerator.getKJEZIC_TOKEN(), 1);

        assertNotNull(saved);
        assertEquals("content", saved.getContent());
        assertEquals("name", saved.getName());
        assertEquals(1, saved.getId());
    }

    @Test
    public void saveNoteTest_unauthorized() {
        NoteRepository noteRepository = Mockito.mock(NoteRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        RootService rootService = Mockito.mock(RootService.class);

        Note note = NoteGenerator.kjezicNoteToSave();
        Mockito.when(noteRepository.save(Mockito.any())).thenReturn(note);
        Mockito.when(rootService.authorize(UserGenerator.getKJEZIC_TOKEN(), 1)).thenReturn(true);

        NoteService noteService = new NoteService(noteRepository, rootService, userRepository);
        NoteDTO saved = noteService.saveNote(NoteGenerator.noteBody_kjezic(), UserGenerator.getKENDA_TOKEN(), 1);

        assertNull(saved);
    }

}