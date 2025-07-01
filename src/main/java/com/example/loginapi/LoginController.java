package com.example.loginapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());

        if (userOpt.isPresent()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User user = userOpt.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return "Login successful";
            }
        }
        return "Login failed";
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null ||
                user.getUsername().isEmpty() ||
                user.getPassword().isEmpty() ||
                !user.getPassword().matches(".*\\d.*")) {
            return "Invalid username or password. Password must contain at least one number.";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/reserve")
    public String reserve(@RequestBody Reservation reservation) {
        if (reservation.getUsername() == null || reservation.getUsername().isEmpty()) {
            return "Username is required for reservation";
        }

        Optional<User> userOpt = userRepository.findByUsername(reservation.getUsername());
        if (userOpt.isEmpty()) {
            return "User not found";
        }

        reservationRepository.save(reservation);
        return "Reservation created successfully";
    }

}



