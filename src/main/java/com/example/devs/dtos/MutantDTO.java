package com.example.devs.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MutantDTO(
        @NotBlank String name,
        @NotBlank String power,
        @NotNull int age
) {
}
