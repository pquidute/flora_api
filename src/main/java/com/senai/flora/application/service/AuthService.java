package com.senai.flora.application.service;

import com.senai.flora.application.dto.AuthDTO;
import com.senai.flora.domain.enums.UserRole;
import com.senai.flora.domain.model.User;
import com.senai.flora.domain.repository.UserRepository;
import com.senai.flora.infrastructure.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(AuthDTO.LoginRequestDTO requestDTO) {
        User user = userRepository.findByUsername(requestDTO.username())
                .orElseThrow(() -> new RuntimeException("User not found!"));
        if (!passwordEncoder.matches(requestDTO.password(), user.getPassword())){
            throw new RuntimeException("Invalid credentials!");
        }

        return jwtService.generateToken(user.getId(), user.getUsername(), UserRole.valueOf(user.getRole().name()));
    }
}
