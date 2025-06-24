package com.example.loginapi;

import com.example.loginapi.LoginRequest;
import com.example.loginapi.User;
import com.example.loginapi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUsernameAndPassword(
                loginRequest.getUsername(), loginRequest.getPassword());

        return user.isPresent() ? "Login successful" : "Invalid credentials";
    }
}
