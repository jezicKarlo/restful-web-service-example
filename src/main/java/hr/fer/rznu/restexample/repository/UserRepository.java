package hr.fer.rznu.restexample.repository;

import hr.fer.rznu.restexample.entity.User;


public interface UserRepository {

    User getUser(Integer id);
    void createUser(User user);
    User updateUser(User user);
    User deleteUser(Integer id);
}
