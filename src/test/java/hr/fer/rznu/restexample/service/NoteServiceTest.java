package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.NoteDTO;
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

}