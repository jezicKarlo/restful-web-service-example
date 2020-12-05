package hr.fer.rznu.restexample.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class User {

    private UUID UUID;
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
}
