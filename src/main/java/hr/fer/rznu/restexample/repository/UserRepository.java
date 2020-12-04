package hr.fer.rznu.restexample.repository;

import hr.fer.rznu.restexample.entity.User;

import java.util.Optional;


public interface UserRepository {

    User getUserById(Integer id);
    Optional<User> getByUsername(String username);
    void createUser(User user);
    User updateUser(User user);
    User deleteUser(Integer id);
}
