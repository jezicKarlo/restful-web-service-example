package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginForm;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RootService {

    private final UserRepository repository;

    public RootService(UserRepository repository) {
        this.repository = repository;
    }

    public UUID authenticate(LoginForm loginForm) {
        Optional<User> optionalUser = repository.getByUsername(loginForm.getUsername());
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        if (loginForm.getPassword().equals(user.getPassword())) {
            return user.getUUID();
        }
        return null;
    }

    public boolean authorize(UUID uuid, Integer id) {
        User user = repository.getUserById(id);
        return user.getUUID() == uuid;
    }
}
