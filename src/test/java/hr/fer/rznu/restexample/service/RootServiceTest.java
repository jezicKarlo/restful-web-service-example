package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginResponse;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import hr.fer.rznu.restexample.utils.UserGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RootServiceTest {

    @Test
    public void authenticateTest() {
        User user = UserGenerator.createKjezic();
        UserRepository repository = Mockito.mock(UserRepository.class);

        Mockito.when(repository.findByUsername("kjezic")).thenReturn(Optional.of(user));

        RootService service = new RootService(repository);
        LoginResponse response = service.authenticate("kjezic", "1234");
        assertNotNull(response);
    }

    @Test
    public void authorizeTest() {
        User user = UserGenerator.createKjezic();
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(user));

        RootService service = new RootService(repository);
        assertTrue(service.authorize(user.getToken(), 1));
    }

    @Test
    public void isAdminTest() {
        User admin = UserGenerator.createAdmin();
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.findByUsername("admin")).thenReturn(Optional.of(admin));

        RootService service = new RootService(repository);
        assertTrue(service.isAdmin(admin.getToken()));
    }

    @Test
    public void userExistsTest() {
        User user = UserGenerator.createKjezic();
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(user));

        RootService service = new RootService(repository);
        assertTrue(service.userExists(user.getId()));
        assertFalse(service.userExists(2));
    }

    @Test
    public void authorizeTest_username_token() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.findByUsername("kjezic")).thenReturn(Optional.of(UserGenerator.createKjezic()));
        Mockito.when(repository.findByUsername("jurica")).thenReturn(Optional.of(UserGenerator.createJkenda()));

        RootService service = new RootService(repository);
        assertTrue(service.authorize(UserGenerator.getKJEZIC_TOKEN(), "kjezic"));
        assertFalse(service.authorize(UserGenerator.getKJEZIC_TOKEN(), "jurica"));
    }

    @Test
    public void userExistsTest_username() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.findByUsername("kjezic")).thenReturn(Optional.of(UserGenerator.createKjezic()));

        RootService service = new RootService(repository);
        assertTrue(service.userExists("kjezic"));
    }

    @Test
    public void userExistsTest_username_false() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.findByUsername("kjezic")).thenReturn(Optional.empty());

        RootService service = new RootService(repository);
        assertFalse(service.userExists("kjezic"));
    }
}