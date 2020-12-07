package hr.fer.rznu.restexample.utils;

import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.request.EditUser;

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

    public static User createKjezic() {
        User user = new User();
        user.setFirstName("Karlo");
        user.setLastName("Jezic");
        user.setId(1);
        user.setPassword("1234");
        user.setUsername("kjezic");
        user.setRole("user");
        user.setToken("20aa0ab9-b698-4786-96f5-9f81302ef576");
        return user;
    }

    public static EditUser createKjezic_toEdit() {
        EditUser user = new EditUser();
        user.setFirstName("Karlo");
        user.setLastName("Jezic");
        user.setUsername("karlo.jezic");
        return user;
    }

    public static User createJkenda() {
        User user = new User();
        user.setFirstName("Jurica");
        user.setLastName("Kenda");
        user.setId(1);
        user.setPassword("1234");
        user.setUsername("jkenda");
        user.setRole("user");
        user.setToken("b34528ac-434f-4367-90bb-45c5c2828074");
        return user;
    }
}
