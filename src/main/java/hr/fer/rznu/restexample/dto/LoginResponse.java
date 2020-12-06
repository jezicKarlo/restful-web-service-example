package hr.fer.rznu.restexample.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private Integer id;
}
