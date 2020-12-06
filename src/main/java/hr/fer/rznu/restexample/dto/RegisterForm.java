package hr.fer.rznu.restexample.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterForm {

    @NotBlank
    private String username;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String password;
}
