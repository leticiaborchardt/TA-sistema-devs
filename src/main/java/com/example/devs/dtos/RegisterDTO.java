package com.example.devs.dtos;

import com.example.devs.models.UserRole;

public record RegisterDTO(
        String login,
        String password,
        UserRole role
) {
}
