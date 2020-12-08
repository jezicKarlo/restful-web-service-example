package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.NoteBody;
import hr.fer.rznu.restexample.dto.NoteDTO;
import hr.fer.rznu.restexample.entity.Note;
import hr.fer.rznu.restexample.repository.NoteRepository;
import hr.fer.rznu.restexample.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository repository;
    private final RootService rootService;
    private final UserRepository userRepository;


    public NoteService(NoteRepository repository, RootService rootService, UserRepository userRepository) {
        this.repository = repository;
        this.rootService = rootService;
        this.userRepository = userRepository;
    }

    public List<NoteDTO> getNotes(String token, Integer id) {
        if (!rootService.authorize(token, id)) {
            return null;
        }
        List<Note> notes = repository.getAllByUserId(id);

        return notes.stream()
                .map(NoteDTO::new)
                .collect(Collectors.toList());
    }

    public NoteDTO saveNote(NoteBody note, String token, Integer id) {
        if (!rootService.authorize(token, id)) {
            return null;
        }
        Note toSave = new Note(note);
        toSave.setUser(userRepository.getById(id));
        return new NoteDTO(repository.save(toSave));
    }
}
