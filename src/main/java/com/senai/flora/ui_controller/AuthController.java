package com.senai.flora.ui_controller;

import com.senai.flora.application.dto.AuthDTO;
import com.senai.flora.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO.LoginRequestDTO requestDTO){
        String token = authService.login(requestDTO);
        return ResponseEntity.ok(new AuthDTO.TokenResponseDTO(token));
    }
}
