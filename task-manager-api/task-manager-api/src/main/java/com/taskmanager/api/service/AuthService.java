package com.taskmanager.api.service;

import com.taskmanager.api.dto.AuthRequestDTO;
import com.taskmanager.api.entity.User;
import com.taskmanager.api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(AuthRequestDTO authRequestDTO) {
        if (userRepository.existsByUsername(authRequestDTO.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(authRequestDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));

        return userRepository.save(newUser);
    }
}