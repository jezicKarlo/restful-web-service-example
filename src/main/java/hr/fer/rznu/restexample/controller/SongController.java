package hr.fer.rznu.restexample.controller;

import hr.fer.rznu.restexample.service.SongService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/{userId}/playlists/{playlistId}/songs")
public class SongController {

    private final SongService service;

    public SongController(SongService service) {
        this.service = service;
    }
}
