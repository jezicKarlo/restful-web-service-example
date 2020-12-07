package hr.fer.rznu.restexample.repository;

import hr.fer.rznu.restexample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    User getByUsername(String username);
    User getById(Integer id);
    Optional<User> findByUsername(String username);
}
