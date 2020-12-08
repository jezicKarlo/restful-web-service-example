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

    public NoteDTO getNote(Integer id) {
        return new NoteDTO(repository.getById(id));
    }

    public boolean noteExists(Integer id) {
        return repository.findById(id).isPresent();
    }

    public NoteDTO editNote(NoteBody noteBody, Integer id) {
        Note noteToEdit = repository.getById(id);
        noteToEdit.setContent(noteBody.getContent());
        noteToEdit.setName(noteBody.getName());
        Note saved = repository.save(noteToEdit);
        return new NoteDTO(saved);
    }
}
