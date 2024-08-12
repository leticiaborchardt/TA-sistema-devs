package com.example.devs.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "defeated_enemies_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefeatedEnemiesLog implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Mutant mutant;

    private LocalDate date;

    private int numberOfEnemies;
}
