package hr.fer.rznu.restexample.utils;

import hr.fer.rznu.restexample.entity.User;

import java.util.UUID;

public class UserGenerator {

    public static User createAdmin() {
        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setId(2);
        user.setPassword("1234");
        user.setUsername("admin");
        user.setRole("admin");
        user.setToken(UUID.randomUUID().toString());
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
        user.setToken(UUID.randomUUID().toString());
        return user;
    }
}
