package com.example.devs.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "mutant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mutant implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String power;
    private int age;

    @OneToOne
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "mutant")
    @JsonBackReference
    private List<DefeatedEnemiesLog> defeatedEnemiesLogs;

    public Mutant(String name, String power, int age, User user) {
        this.name = name;
        this.power = power;
        this.age = age;
        this.user = user;
    }
}
