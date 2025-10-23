package com.senai.flora.infrastructure.config;

import com.senai.flora.domain.enums.UserRole;
import com.senai.flora.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminBootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminBootstrap(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${system.admin.username}")
    private String adminUsername;

    @Value("${system.admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        userRepository.findByUsername(adminUsername).ifPresentOrElse(
                user -> {},
                () -> {
                    var adminUser = new com.senai.flora.domain.model.User();
                    adminUser.setUsername(adminUsername);
                    adminUser.setPassword(passwordEncoder.encode(adminPassword));
                    adminUser.setRole(UserRole.ADMIN);
                    userRepository.save(adminUser);
                    System.out.println("Admin user created with username: '" + adminUsername + "' and password: '" + adminPassword + "'");
                }
        );
    }
}
