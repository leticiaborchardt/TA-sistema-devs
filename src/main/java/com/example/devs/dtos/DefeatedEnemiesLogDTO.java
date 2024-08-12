package com.example.devs.dtos;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record DefeatedEnemiesLogDTO(
        @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
        @NotNull int numberOfEnemies,
        @NotNull Long mutantId
) {
}
