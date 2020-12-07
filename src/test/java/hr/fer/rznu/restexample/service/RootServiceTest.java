package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.LoginResponse;
import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.repository.UserRepository;
import hr.fer.rznu.restexample.utils.UserGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class RootServiceTest {

    @Test
    public void authenticateTest() {
        User user = UserGenerator.createKjezic();
        UserRepository repository = Mockito.mock(UserRepository.class);

        Mockito.when(repository.getByUsername("kjezic")).thenReturn(user);

        RootService service = new RootService(repository);
        LoginResponse response = service.authenticate("kjezic", "1234");
        assertNotNull(response);
    }

    @Test
    public void authorizeTest() {
        User user = UserGenerator.createKjezic();
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getById(1)).thenReturn(user);

        RootService service = new RootService(repository);
        assertNotNull(service.authorize(user.getToken(), 1));
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
        User user = UserGenerator.createKjezic();
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getById(1)).thenReturn(user);

        RootService service = new RootService(repository);
        assertTrue(service.userExists(user.getId()));
        assertFalse(service.userExists(2));
    }

    @Test
    public void authorizeTest_username_token() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getByUsername("kjezic")).thenReturn(UserGenerator.createKjezic());
        Mockito.when(repository.getByUsername("jurica")).thenReturn(UserGenerator.createJkenda());

        RootService service = new RootService(repository);
        assertTrue(service.authorize("20aa0ab9-b698-4786-96f5-9f81302ef576", "kjezic"));
        assertFalse(service.authorize("20aa0ab9-b698-4786-96f5-9f81302ef576", "jurica"));
    }

    @Test
    public void userExistsTest_username() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getByUsername("kjezic")).thenReturn(UserGenerator.createKjezic());

        RootService service = new RootService(repository);
        assertTrue(service.userExists("kjezic"));
    }

    @Test
    public void userExistsTest_username_false() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getByUsername("kjezic")).thenReturn(null);

        RootService service = new RootService(repository);
        assertFalse(service.userExists("kjezic"));
    }
}