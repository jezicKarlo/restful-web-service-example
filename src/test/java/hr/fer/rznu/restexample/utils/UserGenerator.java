package hr.fer.rznu.restexample.utils;

import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.request.EditUser;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UserGenerator {

    private final String KJEZIC_TOKEN = "20aa0ab9-b698-4786-96f5-9f81302ef576";
    private final String KENDA_TOKEN = "b34528ac-434f-4367-90bb-45c5c2828074";

    public User createAdmin() {
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

    public User createKjezic() {
        User user = new User();
        user.setFirstName("Karlo");
        user.setLastName("Jezic");
        user.setId(1);
        user.setPassword("1234");
        user.setUsername("kjezic");
        user.setRole("user");
        user.setToken(KJEZIC_TOKEN);
        return user;
    }

    public EditUser createKjezic_toEdit() {
        EditUser user = new EditUser();
        user.setFirstName("Karlo");
        user.setLastName("Jezic");
        user.setUsername("karlo.jezic");
        return user;
    }

    public User createJkenda() {
        User user = new User();
        user.setFirstName("Jurica");
        user.setLastName("Kenda");
        user.setId(1);
        user.setPassword("1234");
        user.setUsername("jkenda");
        user.setRole("user");
        user.setToken(KENDA_TOKEN);
        return user;
    }

    public static String getKJEZIC_TOKEN() {
        return KJEZIC_TOKEN;
    }

    public static String getKENDA_TOKEN() {
        return KENDA_TOKEN;
    }
}
