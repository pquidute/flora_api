package com.senai.flora.ui_controller;

import com.senai.flora.application.service.AuthService;
import com.senai.flora.domain.model.User;
import com.senai.flora.infrastructure.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload){
        String username = payload.get("username");
        String password = payload.get("password");
        User user = authService.authenticate(username, password);
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> payload){
        String name = payload.get("name");
        String username = payload.get("name");
        String password = payload.get("name");
        User user = authService.register(name, username, password);
        return ResponseEntity.ok(
                Map.of(
                        "message", "user successfully registered!",
                        "id", user.getId()
                ));
    }

}
