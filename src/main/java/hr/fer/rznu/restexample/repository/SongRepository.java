package hr.fer.rznu.restexample.repository;

import hr.fer.rznu.restexample.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Playlist, Integer> {
}
