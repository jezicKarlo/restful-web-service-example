package hr.fer.rznu.restexample.repository;

import hr.fer.rznu.restexample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
