package hr.fer.rznu.restexample.repository;

import hr.fer.rznu.restexample.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {


}
