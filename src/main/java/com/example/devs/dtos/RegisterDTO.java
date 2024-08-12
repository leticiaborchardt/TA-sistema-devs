package com.example.devs.dtos;

import com.example.devs.models.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotBlank String login,
        @NotBlank String password,
        @NotNull UserRole role,
        @NotBlank String mutantName,
        @NotBlank String mutantPower,
        @NotNull int mutantAge
) {
}
