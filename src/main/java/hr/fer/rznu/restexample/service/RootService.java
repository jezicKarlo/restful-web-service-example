package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginForm;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RootService {

    private final UserRepository repository;

    public RootService(UserRepository repository) {
        this.repository = repository;
    }

    public UUID authenticate(LoginForm loginForm) {
        User user = repository.getByUsername(loginForm.getUsername());
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(loginForm.getPassword())) {
            return user.getUUID();
        }
        return null;
    }

    public boolean authorize(UUID uuid, Integer id) {
        User user = repository.getUserById(id);
        return user.getUUID() == uuid;
    }

    public boolean isAdmin(UUID uuid) {
        User admin = repository.getByUsername("admin");
        return admin.getUUID() == uuid;
    }
}
