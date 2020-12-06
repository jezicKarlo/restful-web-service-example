package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginResponse;
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

    public LoginResponse register(RegisterForm registerForm) {
        User user = repository.getByUsername(registerForm.getUsername());
        LoginResponse loginResponse = new LoginResponse();
        if (user != null) {
            return loginResponse;
        }
        User save = repository.save(createNewUser(registerForm));
        loginResponse.setId(save.getId());
        loginResponse.setToken(save.getToken());
        return loginResponse;
    }

    public boolean deleteUser(Integer id) {
        if (repository.getById(id) == null) {
            return false;
        }
        repository.deleteById(id);
        return true;
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
