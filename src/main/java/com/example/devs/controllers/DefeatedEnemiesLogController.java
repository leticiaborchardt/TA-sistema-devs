package com.example.devs.controllers;

import com.example.devs.dtos.DefeatedEnemiesLogDTO;
import com.example.devs.models.DefeatedEnemiesLog;
import com.example.devs.models.Mutant;
import com.example.devs.repositories.DefeatedEnemiesLogRepository;
import com.example.devs.repositories.MutantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("defeated-enemies")
public class DefeatedEnemiesLogController {

    @Autowired
    DefeatedEnemiesLogRepository defeatedEnemiesLogRepository;

    @Autowired
    MutantRepository mutantRepository;

    @GetMapping
    public ResponseEntity<List<DefeatedEnemiesLog>> getAllLogs() {
        return ResponseEntity.status(HttpStatus.OK).body(defeatedEnemiesLogRepository.findAll());
    }

    @GetMapping("/{mutantId}")
    public ResponseEntity<List<DefeatedEnemiesLog>> getLogsByMutantId(@PathVariable Long mutantId) {
        return ResponseEntity.status(HttpStatus.OK).body(defeatedEnemiesLogRepository.findAllByMutantId(mutantId));
    }

    @PostMapping
    public ResponseEntity<DefeatedEnemiesLog> createLog(@RequestBody @Valid DefeatedEnemiesLogDTO logDTO) {
        DefeatedEnemiesLog log = new DefeatedEnemiesLog();

        Mutant mutant = mutantRepository.findById(logDTO.mutantId())
                .orElseThrow(() -> new RuntimeException("Mutant not found."));

        BeanUtils.copyProperties(logDTO, log);
        log.setMutant(mutant);

        return ResponseEntity.status(HttpStatus.CREATED).body(defeatedEnemiesLogRepository.save(log));
    }
}
