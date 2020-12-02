package hr.fer.rznu.restexample.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    @OneToMany
    private List<Playlist> playlists;
}
