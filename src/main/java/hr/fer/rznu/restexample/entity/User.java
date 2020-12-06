package hr.fer.rznu.restexample.entity;

import hr.fer.rznu.restexample.request.EditUser;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String role;

    public void edit(EditUser editUser) {
        username = editUser.getUsername();
        firstName = editUser.getFirstName();
        lastName = editUser.getLastName();
    }
}
