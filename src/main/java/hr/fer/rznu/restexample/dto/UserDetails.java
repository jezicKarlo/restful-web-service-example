package hr.fer.rznu.restexample.dto;

import hr.fer.rznu.restexample.entity.User;
import lombok.Data;

@Data
public class UserDetails {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;

    public UserDetails(User user) {
        username = user.getUsername();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        id = user.getId();
    }
}
