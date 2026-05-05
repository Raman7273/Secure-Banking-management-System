package com.raman.bankingsystem.app.controller;

import com.raman.bankingsystem.app.dto.LoginRequest;
import com.raman.bankingsystem.app.dto.RegisterRequest;
import com.raman.bankingsystem.app.entity.User;
import com.raman.bankingsystem.app.security.JwtService;
import com.raman.bankingsystem.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        User user = userService.loginUser(request);

        // generate token
        String token = jwtService.generateToken(user.getEmail());

        return token;
    }
}