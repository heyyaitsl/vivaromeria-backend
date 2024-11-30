package tfg.romerias.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tfg.romerias.security.TokenService;
import tfg.romerias.user.converter.UserConverter;
import tfg.romerias.user.model.User;
import tfg.romerias.user.model.UserRequest;
import tfg.romerias.user.model.UserResponse;
import tfg.romerias.user.service.UserService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "http://localhost:5173")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService; // Inyecta TokenService
    private  final UserConverter converter;


    public UserController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService, UserConverter converter) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.converter = converter;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest){
        User savedUser = userService.saveUser(converter.convertFromRequest(userRequest));
        return ResponseEntity.ok(converter.convertToResponse(savedUser));
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest){
        User savedUser = userService.saveUser(converter.convertFromRequest(userRequest));
        return ResponseEntity.ok(converter.convertToResponse(savedUser));
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            User user = userService.getUserByUsername(loginRequest.getUsername());
            String token = tokenService.generateToken(user.getUsername(), user.getRole());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("username", user.getUsername());
            response.put("role", user.getRole());
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Login failed"));
        }
    }
    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) {
        return ResponseEntity.ok(converter.convertToResponse(userService.getUserByUsername(username)));
    }
}
