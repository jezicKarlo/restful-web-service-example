package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginResponse;
import hr.fer.rznu.restexample.dto.RegisterForm;
import hr.fer.rznu.restexample.dto.UserDetails;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import hr.fer.rznu.restexample.request.EditUser;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDetails getUserById(Integer id) {
        User user = repository.getById(id);
        if (user == null) {
            return null;
        }
        return new UserDetails(user);
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
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    public UserDetails editUser(EditUser edit, Integer id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        User userToEdit = user.get();
        userToEdit.edit(edit);
        return new UserDetails(repository.save(userToEdit));
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
