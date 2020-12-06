package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginResponse;
import hr.fer.rznu.restexample.dto.RegisterForm;
import hr.fer.rznu.restexample.dto.UserDTO;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import hr.fer.rznu.restexample.utils.UserGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    public void getUserByIdTest() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getById(1)).thenReturn(UserGenerator.createUser());

        UserService service = new UserService(repository);
        UserDTO user = service.getUserById(1);
        assertEquals("kjezic", user.getUsername());
    }

    @Test
    public void registerTest() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        User user = UserGenerator.createUser();
        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        UserService service = new UserService(repository);
        RegisterForm registerForm = createRegisterForm();
        LoginResponse response = service.register(registerForm);
        assertEquals(1, response.getId());
        assertEquals("20aa0ab9-b698-4786-96f5-9f81302ef576", response.getToken());
    }

    @Test
    public void registerTest_conflict() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        User user = UserGenerator.createUser();
        Mockito.when(repository.getByUsername("kjezic")).thenReturn(user);

        UserService service = new UserService(repository);
        RegisterForm registerForm = createRegisterForm();
        LoginResponse response = service.register(registerForm);
        assertNull(response.getToken());
    }

    private RegisterForm createRegisterForm() {
        RegisterForm registerForm = new RegisterForm();
        registerForm.setFirstName("Karlo");
        registerForm.setLastName("Jezic");
        registerForm.setPassword("123");
        registerForm.setUsername("kjezic");
        return registerForm;
    }

}