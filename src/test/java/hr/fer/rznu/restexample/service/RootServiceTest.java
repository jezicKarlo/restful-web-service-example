package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginForm;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RootServiceTest {

    @Test
    public void authenticateTest() {
        User user = createUser();
        LoginForm loginForm = createLoginForm();

        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getByUsername("karlo")).thenReturn(user);

        RootService service = new RootService(repository);
        UUID uuid = service.authenticate(loginForm);
        assertNotNull(uuid);
    }

    private LoginForm createLoginForm() {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("karlo");
        loginForm.setPassword("1234");
        return loginForm;
    }

    private User createUser() {
        User user = new User();
        user.setFirstName("Karlo");
        user.setLastName("Jezic");
        user.setId(1);
        user.setPassword("1234");
        user.setUUID(UUID.randomUUID());
        return user;
    }

}