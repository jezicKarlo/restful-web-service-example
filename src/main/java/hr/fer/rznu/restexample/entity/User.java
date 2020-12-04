package hr.fer.rznu.restexample.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private int id;

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private List<Note> notes;
}
