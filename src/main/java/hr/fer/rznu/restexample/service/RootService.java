package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginResponse;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RootService {

    private final UserRepository repository;

    public RootService(UserRepository repository) {
        this.repository = repository;
    }

    public LoginResponse authenticate(String username, String password) {
        User user = repository.getByUsername(username);
        LoginResponse response = new LoginResponse();
        if (user == null) {
            return response;
        }
        response.setId(user.getId());
        if (user.getPassword().equals(password)) {
            response.setToken(user.getToken());
        }
        return response;
    }

    public boolean authorize(String token, Integer id) {
        try {
            User user = repository.getById(id);
            return user.getToken().equals(token);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean authorize(String token, String username) {
        try {
            User user = repository.getByUsername(username);
            return user.getToken().equals(token);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isAdmin(String token) {
        try {
            User admin = repository.getByUsername("admin");
            return admin.getToken().equals(token);
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    public boolean userExists(Integer id) {
        return repository.getById(id) != null;
    }
}
