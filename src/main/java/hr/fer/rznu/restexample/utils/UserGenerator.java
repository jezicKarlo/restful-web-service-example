package hr.fer.rznu.restexample.utils;

import hr.fer.rznu.restexample.entity.User;
import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class UserGenerator {

    public static Map<Integer, User> generateUsers() {
        Map<Integer, User> users = new HashMap<>();
        User admin = createAdmin();
        User user = createUser();

        users.put(admin.getId(), admin);
        users.put(user.getId(), user);

        return users;
    }

    public static User createAdmin() {
        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setId(2);
        user.setPassword("1234");
        user.setUsername("admin");
        user.setRole("admin");
        user.setUUID(UUID.randomUUID());
        return user;
    }

    public static User createUser() {
        User user = new User();
        user.setFirstName("Karlo");
        user.setLastName("Jezic");
        user.setId(1);
        user.setPassword("1234");
        user.setUsername("kjezic");
        user.setRole("user");
        user.setUUID(UUID.randomUUID());
        return user;
    }
}
