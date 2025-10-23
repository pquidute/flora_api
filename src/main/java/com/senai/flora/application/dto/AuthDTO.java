package com.senai.flora.application.dto;

public class AuthDTO {

    public record LoginRequestDTO(
            String username,
            String password
    ) {}

    public record TokenResponseDTO(
            String token
    ) {}

}
