package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginResponse;
import hr.fer.rznu.restexample.dto.UserDetails;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RootService {

    private final UserRepository repository;

    public RootService(UserRepository repository) {
        this.repository = repository;
    }

    public LoginResponse authenticate(String username, String password) {
        Optional<User> user = repository.findByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        if (user.get().getPassword().equals(password)) {
            LoginResponse response = new LoginResponse();
            response.setId(user.get().getId());
            response.setToken(user.get().getToken());
            return response;
        }
        return null;
    }

    public boolean authorize(String token, Integer id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            return false;
        }
        return user.get().getToken().equals(token);
    }

    public boolean authorize(String token, String username) {
        Optional<User> user = repository.findByUsername(username);
        if (user.isEmpty()) {
            return false;
        }
        return user.get().getToken().equals(token);
    }

    public boolean isAdmin(String token) {
        Optional<User> admin = repository.findByUsername("admin");
        if (admin.isEmpty()) {
            return false;
        }
        return admin.get().getToken().equals(token);
    }

    public boolean userExists(Integer id) {
        return repository.findById(id).isPresent();
    }

    public boolean userExists(String username) {
        return repository.findByUsername(username).isPresent();
    }
}
