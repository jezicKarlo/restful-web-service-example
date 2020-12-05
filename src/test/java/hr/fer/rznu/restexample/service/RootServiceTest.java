package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginForm;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import hr.fer.rznu.restexample.utils.UserGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RootServiceTest {

    @Test
    public void authenticateTest() {
        User user = UserGenerator.createUser();
        LoginForm loginForm = createLoginForm();
        UserRepository repository = Mockito.mock(UserRepository.class);

        Mockito.when(repository.getByUsername("kjezic")).thenReturn(user);

        RootService service = new RootService(repository);
        UUID uuid = service.authenticate(loginForm);
        assertNotNull(uuid);
    }

    @Test
    public void authorizeTest() {
        User user = UserGenerator.createUser();
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getById(1)).thenReturn(user);

        RootService service = new RootService(repository);
        assertTrue(service.authorize(user.getUUID(), 1));
    }

    @Test
    public void isAdminTest() {
        User admin = UserGenerator.createAdmin();
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getByUsername("admin")).thenReturn(admin);

        RootService service = new RootService(repository);
        assertTrue(service.isAdmin(admin.getUUID()));
    }

    private LoginForm createLoginForm() {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("kjezic");
        loginForm.setPassword("1234");
        return loginForm;
    }

}