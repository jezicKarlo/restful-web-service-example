package hr.fer.rznu.restexample.controller;

import hr.fer.rznu.restexample.service.NoteService;
import hr.fer.rznu.restexample.service.RootService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/{userId}/playlists")
public class NoteController {

    private final NoteService noteService;
    private final RootService rootService;

    public NoteController(NoteService noteService, RootService rootService) {
        this.noteService = noteService;
        this.rootService = rootService;
    }
}
