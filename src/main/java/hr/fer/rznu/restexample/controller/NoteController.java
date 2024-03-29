package hr.fer.rznu.restexample.controller;

import hr.fer.rznu.restexample.dto.NoteBody;
import hr.fer.rznu.restexample.dto.NoteDTO;
import hr.fer.rznu.restexample.dto.Response;
import hr.fer.rznu.restexample.dto.UserNotes;
import hr.fer.rznu.restexample.service.NoteService;
import hr.fer.rznu.restexample.service.RootService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/notes")
public class NoteController {

    private final NoteService noteService;
    private final RootService rootService;

    public NoteController(NoteService noteService, RootService rootService) {
        this.noteService = noteService;
        this.rootService = rootService;
    }

    @GetMapping
    public ResponseEntity<Response<UserNotes>> getUserNotes(@PathVariable("userId") Integer id,
                                                            @NotBlank String token) {

        if (!rootService.userExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<NoteDTO> notes = noteService.getNotes(token, id);
        if (notes == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(new Response<>(new UserNotes(notes)));
    }

    @PostMapping
    public ResponseEntity<Response<NoteDTO>> addNote(@PathVariable("userId") Integer id,
                                                     @Valid @RequestBody NoteBody note,
                                                     @NotBlank String token) {

        if (!rootService.userExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        NoteDTO saved = noteService.saveNote(note, token, id);
        if (saved == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>(saved));
    }

    @GetMapping("{noteId}")
    public ResponseEntity<Response<NoteDTO>> getNote(@PathVariable("userId") Integer userId,
                                                     @PathVariable("noteId") Integer noteId,
                                                     @NotBlank String token) {

        HttpStatus status = status(userId, token, noteId);
        if (status != null) {
            return ResponseEntity.status(status).body(null);
        }
        NoteDTO note = noteService.getNote(noteId);
        return ResponseEntity.ok(new Response<>(note));
    }

    @PutMapping("{noteId}")
    public ResponseEntity<Response<NoteDTO>> editNote(@PathVariable("userId") Integer userId,
                                                      @PathVariable("noteId") Integer noteId,
                                                      @Valid @RequestBody NoteBody edit,
                                                      @NotBlank String token) {

        HttpStatus status = status(userId, token, noteId);
        if (status != null) {
            return ResponseEntity.status(status).body(null);
        }
        NoteDTO noteDTO = noteService.editNote(edit, noteId);
        return ResponseEntity.ok(new Response<>(noteDTO));
    }

    @DeleteMapping("{noteId}")
    public ResponseEntity<Response<String>> deleteNote(@PathVariable("userId") Integer userId,
                                                       @PathVariable("noteId") Integer noteId,
                                                       @NotBlank String token) {

        HttpStatus status = status(userId, token, noteId);
        if (status != null) {
            return ResponseEntity.status(status).body(null);
        }
        noteService.deleteNote(noteId);
        return ResponseEntity.ok(new Response<>("Note deleted"));
    }

    private HttpStatus status(Integer userId, String token, Integer noteId) {
        if (!rootService.userExists(userId)) {
            return HttpStatus.NOT_FOUND;
        }
        if (!rootService.authorize(token, userId)) {
            return HttpStatus.UNAUTHORIZED;
        }
        if (!noteService.noteExists(noteId)) {
            return HttpStatus.NOT_FOUND;
        }
        return null;
    }
}
