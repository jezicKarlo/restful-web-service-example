package hr.fer.rznu.restexample.controller;

import hr.fer.rznu.restexample.dto.LoginResponse;
import hr.fer.rznu.restexample.dto.RegisterForm;
import hr.fer.rznu.restexample.dto.Response;
import hr.fer.rznu.restexample.dto.UserDTO;
import hr.fer.rznu.restexample.service.RootService;
import hr.fer.rznu.restexample.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    public ResponseEntity<Response<UserDTO>> profile(@NotNull @PathVariable("userId") Integer userId,
                                           @NotBlank String token) {
        if (!rootService.userExists(userId)) {
            return ResponseEntity.notFound().build();
        }
        if (!rootService.authorize(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(new Response<>(userService.getUserById(userId)));
    }

    @GetMapping
    public ResponseEntity<Response<LoginResponse>> login(@NotBlank String username,
                                               @NotBlank String password) {
        LoginResponse response = rootService.authenticate(username, password);
        if (response.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (response.getToken() == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.ok(new Response<>(response));
    }

    @PostMapping
    public ResponseEntity<Response<LoginResponse>> register(@Valid @RequestBody RegisterForm registerForm) {
        LoginResponse response = userService.register(registerForm);
        if (response.getId() == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>(response));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Response<String>> delete(@PathVariable("userId") Integer id,
                                                   @NotBlank String token) {
        if (!rootService.authorize(token, id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        if (userService.deleteUser(id)) {
            return ResponseEntity.ok(new Response<>("User deleted"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>("User doesn't exist"));

    }
}
