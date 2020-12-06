package hr.fer.rznu.restexample.service;

import hr.fer.rznu.restexample.dto.UserDTO;
import hr.fer.rznu.restexample.repository.UserRepository;
import hr.fer.rznu.restexample.utils.UserGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

}