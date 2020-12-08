package hr.fer.rznu.restexample.repository;

import hr.fer.rznu.restexample.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> getAllByUserId(Integer id);
    Note getById(Integer id);

}
