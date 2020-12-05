package hr.fer.rznu.restexample.repository;

import hr.fer.rznu.restexample.entity.User;

import java.util.List;


public interface UserRepository {

    User getById(Integer id);
    User getByUsername(String username);
    void createUser(User user);
    User updateUser(User user);
    User deleteUser(Integer id);
    List<User> allUsers();
}
