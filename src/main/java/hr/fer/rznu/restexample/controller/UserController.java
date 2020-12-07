package hr.fer.rznu.restexample.controller;

import hr.fer.rznu.restexample.dto.LoginResponse;
import hr.fer.rznu.restexample.dto.RegisterForm;
import hr.fer.rznu.restexample.dto.Response;
import hr.fer.rznu.restexample.dto.UserDetails;
import hr.fer.rznu.restexample.request.EditUser;
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
    public ResponseEntity<Response<UserDetails>> profile(@NotNull @PathVariable("userId") Integer userId,
                                                         @NotBlank String token) {
        if (!rootService.userExists(userId)) {
            return ResponseEntity.notFound().build();
        }
        UserDetails userDetails = rootService.authorize(token, userId);
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(new Response<>(userDetails));
    }

    @GetMapping
    public ResponseEntity<Response<LoginResponse>> login(@NotBlank String username,
                                                         @NotBlank String password) {

        if (!rootService.userExists(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        LoginResponse response = rootService.authenticate(username, password);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.ok(new Response<>(response));
    }

    @PostMapping
    public ResponseEntity<Response<LoginResponse>> register(@Valid @RequestBody RegisterForm registerForm) {
        if (rootService.userExists(registerForm.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        LoginResponse response = userService.register(registerForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>(response));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Response<String>> delete(@PathVariable("userId") Integer id,
                                                   @NotBlank String token) {
        if (!rootService.userExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>("User doesn't exist"));
        }
        UserDetails userDetails = rootService.authorize(token, id);
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response<>("Unauthorized"));
        }
        userService.deleteUser(id);
        return ResponseEntity.ok(new Response<>("User deleted"));
    }

    @PutMapping("{userId}")
    public ResponseEntity<Response<UserDetails>> edit(@PathVariable("userId") Integer id,
                                                      @Valid @RequestBody EditUser edit,
                                                      @NotBlank String token) {

        if (!rootService.userExists(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        UserDetails userDetails = rootService.authorize(token, id);
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        UserDetails edited = userService.editUser(edit, id);
        return ResponseEntity.ok(new Response<>(edited));
    }
}
