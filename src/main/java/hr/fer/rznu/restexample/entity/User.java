package hr.fer.rznu.restexample.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class User {

    private UUID UUID;
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
    @OneToMany
    private List<Note> notes;
}
