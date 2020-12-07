package hr.fer.rznu.restexample.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditUser {

    @NotBlank
    private String username;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
