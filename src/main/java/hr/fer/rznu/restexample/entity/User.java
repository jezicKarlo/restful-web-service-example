package hr.fer.rznu.restexample.entity;

import hr.fer.rznu.restexample.request.EditUser;
import lombok.Data;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;

    public User() {
    }

    public void edit(EditUser editUser) {
        username = editUser.getUsername();
        firstName = editUser.getFirstName();
        lastName = editUser.getLastName();
    }
}
