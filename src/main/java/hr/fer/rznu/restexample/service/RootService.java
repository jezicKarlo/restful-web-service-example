package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginResponse;
import hr.fer.rznu.restexample.dto.UserDetails;
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
        if (user.getPassword().equals(password)) {
            LoginResponse response = new LoginResponse();
            response.setId(user.getId());
            response.setToken(user.getToken());
            return response;
        }
        return null;
    }

    public UserDetails authorize(String token, Integer id) {
            User user = repository.getById(id);
            if (user.getToken().equals(token)) {
                return new UserDetails(user);
            }
            return null;
    }

    public boolean authorize(String token, String username) {
        User user = repository.getByUsername(username);
        return user.getToken().equals(token);
    }

    public boolean isAdmin(String token) {
        User admin = repository.getByUsername("admin");
        return admin.getToken().equals(token);
    }

    public boolean userExists(Integer id) {
        return repository.getById(id) != null;
    }

    public boolean userExists(String username) {
        return repository.getByUsername(username) != null;
    }
}
