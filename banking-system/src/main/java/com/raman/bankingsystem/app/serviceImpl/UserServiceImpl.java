package com.raman.bankingsystem.app.serviceImpl;

import com.raman.bankingsystem.app.dto.LoginRequest;
import com.raman.bankingsystem.app.dto.RegisterRequest;
import com.raman.bankingsystem.app.entity.Role;
import com.raman.bankingsystem.app.entity.User;
import com.raman.bankingsystem.app.repository.UserRepository;
import com.raman.bankingsystem.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterRequest request){
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(true)
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    @Override
    public User loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}