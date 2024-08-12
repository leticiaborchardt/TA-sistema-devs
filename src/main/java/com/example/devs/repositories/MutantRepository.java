package com.example.devs.repositories;

import com.example.devs.models.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MutantRepository extends JpaRepository<Mutant, Long> {
}
