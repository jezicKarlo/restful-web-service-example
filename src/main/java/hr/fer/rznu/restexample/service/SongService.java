package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.repository.SongRepository;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    private final SongRepository repository;

    public SongService(SongRepository repository) {
        this.repository = repository;
    }
}
