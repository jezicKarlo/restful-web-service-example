package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginResponse;
import hr.fer.rznu.restexample.dto.RegisterForm;
import hr.fer.rznu.restexample.dto.UserDetails;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import hr.fer.rznu.restexample.utils.RegisterFormGenerator;
import hr.fer.rznu.restexample.utils.UserGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    @Test
    public void registerTest() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getByUsername("kjezic")).thenReturn(null);
        Mockito.when(repository.save(Mockito.any())).thenReturn(UserGenerator.createKjezic());

        UserService service = new UserService(repository);
        RegisterForm registerForm = RegisterFormGenerator.createRegisterForm();
        LoginResponse response = service.register(registerForm);
        assertEquals(1, response.getId());
        assertEquals("20aa0ab9-b698-4786-96f5-9f81302ef576", response.getToken());
    }

    @Test
    public void editTest() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        User kjezic = UserGenerator.createKjezic();
        Mockito.when(repository.getById(1)).thenReturn(kjezic);
        Mockito.when(repository.save(kjezic)).thenReturn(kjezic);

        UserService userService = new UserService(repository);
        UserDetails edited = userService.editUser(UserGenerator.createKjezic_toEdit(), 1);
        assertEquals("karlo.jezic", edited.getUsername());
    }
}