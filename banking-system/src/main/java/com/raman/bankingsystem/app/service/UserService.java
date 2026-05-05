package com.raman.bankingsystem.app.service;
import com.raman.bankingsystem.app.dto.RegisterRequest;
import com.raman.bankingsystem.app.entity.User;
import com.raman.bankingsystem.app.dto.LoginRequest;

public interface UserService {
    User registerUser(RegisterRequest request);
    User loginUser(LoginRequest request);
}
