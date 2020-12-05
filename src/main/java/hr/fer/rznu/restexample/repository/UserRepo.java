package hr.fer.rznu.restexample.repository;

import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.utils.UserGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserRepo implements UserRepository {

    private Map<Integer, User> users;

    public UserRepo() {
        users = UserGenerator.generateUsers();
    }

    @Override
    public User getById(Integer id) {
        return users.get(id);
    }

    @Override
    public User getByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .collect(Collectors.toList()).get(0);
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

    @Override
    public List<User> allUsers() {
        return new ArrayList<>(users.values());
    }
}
