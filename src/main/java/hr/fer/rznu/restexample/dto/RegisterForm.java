package hr.fer.rznu.restexample.dto;

import lombok.Data;

@Data
public class RegisterForm {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
}
