package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.entity.Note;
import hr.fer.rznu.restexample.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> getNotes(String token, Integer id) {
        return repository.getAllByUserId(id);
    }
}
