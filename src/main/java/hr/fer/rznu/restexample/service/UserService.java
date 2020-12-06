package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.RegisterForm;
import hr.fer.rznu.restexample.dto.UserDTO;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDTO getUserById(Integer id) {
        User user = repository.getById(id);
        return new UserDTO(user);
    }

    public UUID register(RegisterForm registerForm) {
        User save = repository.save(createNewUser(registerForm));
        return UUID.fromString(save.getToken());
    }

    private User createNewUser(RegisterForm registerForm) {
        User user = new User();
        user.setUsername(registerForm.getUsername());
        user.setFirstName(registerForm.getFirstName());
        user.setLastName(registerForm.getLastName());
        user.setPassword(registerForm.getPassword());
        user.setRole("user");
        user.setToken(UUID.randomUUID().toString());
        return user;
    }

}
