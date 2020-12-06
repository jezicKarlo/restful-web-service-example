package hr.fer.rznu.restexample.controller;

import hr.fer.rznu.restexample.dto.LoginResponse;
import hr.fer.rznu.restexample.dto.RegisterForm;
import hr.fer.rznu.restexample.dto.UserDTO;
import hr.fer.rznu.restexample.service.RootService;
import hr.fer.rznu.restexample.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final RootService rootService;

    public UserController(UserService userService, RootService rootService) {
        this.userService = userService;
        this.rootService = rootService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") Integer userId, String token) {
        if (!rootService.userExists(userId)) {
            return ResponseEntity.notFound().build();
        }
        if (!rootService.authorize(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<LoginResponse> login(String username, String password) {
        LoginResponse response = rootService.authenticate(username, password);
        if (response.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (response.getToken() == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UUID> register(@RequestBody RegisterForm registerForm) {
        UUID token = userService.register(registerForm);
        if (token == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
}
