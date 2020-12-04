package hr.fer.rznu.restexample.repository;

import hr.fer.rznu.restexample.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepo implements UserRepository {

    private Map<Integer, User> users;

    public UserRepo() {
        users = new HashMap<>();
    }

    @Override
    public User getUser(Integer id) {
        return users.get(id);
    }

    @Override
    public void createUser(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User updateUser(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User deleteUser(Integer id) {
        return users.remove(id);
    }
}
