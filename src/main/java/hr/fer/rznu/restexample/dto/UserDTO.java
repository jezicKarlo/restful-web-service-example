package hr.fer.rznu.restexample.dto;

import hr.fer.rznu.restexample.entity.User;
import lombok.Data;

@Data
public class UserDTO {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;

    public UserDTO() {
    }

    public UserDTO(User user) {
        username = user.getUsername();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        id = user.getId();
    }
}
