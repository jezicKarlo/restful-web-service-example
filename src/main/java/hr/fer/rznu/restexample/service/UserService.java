package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginResponse;
import hr.fer.rznu.restexample.dto.RegisterForm;
import hr.fer.rznu.restexample.dto.UserDetails;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import hr.fer.rznu.restexample.request.EditUser;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;
    private final RootService rootService;

    public UserService(UserRepository repository, RootService rootService) {
        this.repository = repository;
        this.rootService = rootService;
    }

    public LoginResponse register(RegisterForm registerForm) {
        LoginResponse loginResponse = new LoginResponse();
        User save = repository.save(createNewUser(registerForm));
        loginResponse.setId(save.getId());
        loginResponse.setToken(save.getToken());
        return loginResponse;
    }

    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }

    public UserDetails editUser(EditUser edit, Integer id, String token) {
        if (rootService.authorize(token, id)) {
            User user = repository.getById(id);
            user.edit(edit);
            return new UserDetails(repository.save(user));
        }
        return null;
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

    public UserDetails getUser(Integer id, String token) {
        if (rootService.authorize(token, id)) {
            User user = repository.getById(id);
            return new UserDetails(user);
        }
        return null;
    }

}
