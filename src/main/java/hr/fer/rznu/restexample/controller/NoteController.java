package hr.fer.rznu.restexample.controller;

import hr.fer.rznu.restexample.dto.Response;
import hr.fer.rznu.restexample.entity.Note;
import hr.fer.rznu.restexample.service.NoteService;
import hr.fer.rznu.restexample.service.RootService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/playlists")
public class NoteController {

    private final NoteService noteService;
    private final RootService rootService;

    public NoteController(NoteService noteService, RootService rootService) {
        this.noteService = noteService;
        this.rootService = rootService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Note>>> getNotes(@PathVariable("userId") Integer id,
                                                         @NotBlank String token) {

        if (!rootService.userExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<Note> notes = noteService.getNotes(token, id);
        if (notes == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(new Response<>(notes));
    }
}
