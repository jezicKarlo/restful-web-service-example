package hr.fer.rznu.restexample.request;

import lombok.Data;

@Data
public class EditUser {

    private String username;
    private String firstName;
    private String lastName;
}
