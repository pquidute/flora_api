package com.senai.flora.application.service;

import com.senai.flora.domain.model.User;
import com.senai.flora.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(String username, String password) {
        return userRepository.findByUsername(username).
                filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(()-> new RuntimeException("Invalid credentials!"));
    }

    public User register(String name, String username, String password) {
        User user = new User(name, username, passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}
