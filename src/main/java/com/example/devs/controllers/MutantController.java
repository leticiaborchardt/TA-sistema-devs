package com.example.devs.controllers;

import com.example.devs.dtos.DefeatedEnemiesLogDTO;
import com.example.devs.dtos.RecruitmentResponseDTO;
import com.example.devs.models.DefeatedEnemiesLog;
import com.example.devs.models.Mutant;
import com.example.devs.repositories.MutantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("mutant")
public class MutantController {

    @Autowired
    MutantRepository mutantRepository;

    @GetMapping
    public ResponseEntity<List<Mutant>> getAllMutants() {
        return ResponseEntity.status(HttpStatus.OK).body(mutantRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMutantById(@PathVariable Long id) {
        Optional<Mutant> mutantO = mutantRepository.findById(id);

        if (mutantO.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(mutantO.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutant not found.");
    }

    @GetMapping("/{id}/check-recruitment")
    public ResponseEntity<Object> getRecruitmentStatus(@PathVariable Long id) {
        Optional<Mutant> mutantO = mutantRepository.findById(id);

        if (mutantO.isPresent()) {
            Mutant mutant = mutantO.get();

            LocalDate today = LocalDate.now();
            LocalDate oneWeekAgo = today.minusDays(7);

            int totalWeekDefeated = mutant.getDefeatedEnemiesLogs().stream()
                    .filter(log -> (log.getDate().isAfter(oneWeekAgo) || log.getDate().isEqual(oneWeekAgo))
                            && (log.getDate().isBefore(today) || log.getDate().isEqual(today)))
                    .mapToInt(DefeatedEnemiesLog::getNumberOfEnemies)
                    .sum();

            int totalAliens = (int) ((26.8 / 100) * totalWeekDefeated);
            int totalDemons = (int) ((43.2 / 100) * totalWeekDefeated);

            return ResponseEntity.status(HttpStatus.OK).body(new RecruitmentResponseDTO(totalAliens, totalDemons, totalAliens > 20));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutant not found.");
    }
}
