package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    private final PlaylistRepository repository;

    public PlaylistService(PlaylistRepository repository) {
        this.repository = repository;
    }
}
