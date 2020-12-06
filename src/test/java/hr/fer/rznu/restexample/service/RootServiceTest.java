package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginResponse;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import hr.fer.rznu.restexample.utils.UserGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RootServiceTest {

    @Test
    public void authenticateTest() {
        User user = UserGenerator.createUser();
        UserRepository repository = Mockito.mock(UserRepository.class);

        Mockito.when(repository.getByUsername("kjezic")).thenReturn(user);

        RootService service = new RootService(repository);
        LoginResponse response = service.authenticate("kjezic", "123");
        assertNotNull(response);
    }

    @Test
    public void authorizeTest() {
        User user = UserGenerator.createUser();
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getById(1)).thenReturn(user);

        RootService service = new RootService(repository);
        assertTrue(service.authorize(user.getToken(), 1));
    }

    @Test
    public void isAdminTest() {
        User admin = UserGenerator.createAdmin();
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getByUsername("admin")).thenReturn(admin);

        RootService service = new RootService(repository);
        assertTrue(service.isAdmin(admin.getToken()));
    }

    @Test
    public void userExistsTest() {
        User user = UserGenerator.createUser();
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getById(1)).thenReturn(user);

        RootService service = new RootService(repository);
        assertTrue(service.userExists(user.getId()));
        assertFalse(service.userExists(2));
    }
}