package hr.fer.rznu.restexample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/{userId}/playlists/{playlistId}/songs")
public class SongController {
}
