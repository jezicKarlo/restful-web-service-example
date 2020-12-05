package hr.fer.rznu.restexample.controller;

import hr.fer.rznu.restexample.entity.User;
import hr.fer.rznu.restexample.service.RootService;
import hr.fer.rznu.restexample.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping
    public ResponseEntity<List<User>> allUsers(@RequestParam UUID uuid) {
        if (!rootService.isAdmin(uuid)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<>());
        }
        return ResponseEntity.ok(userService.allUsers());
    }
}
