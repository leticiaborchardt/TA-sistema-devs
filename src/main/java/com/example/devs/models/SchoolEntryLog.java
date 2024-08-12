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
@Table(name = "school_entry_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolEntryLog implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Mutant mutant;

    private LocalDate entryDate;

    private LocalDate exitDate;

    public SchoolEntryLog(Mutant mutant, LocalDate now) {
        this.mutant = mutant;
        this.entryDate = now;
        this.exitDate = null;
    }
}
