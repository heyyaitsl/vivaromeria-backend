package tfg.romerias.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.romerias.user.model.User;
import tfg.romerias.user.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
}
