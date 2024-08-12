package com.example.devs.controllers;

import com.example.devs.models.Mutant;
import com.example.devs.models.SchoolEntryLog;
import com.example.devs.repositories.MutantRepository;
import com.example.devs.repositories.SchoolEntryLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("school-entries")
public class SchoolEntryLogController {

    @Autowired
    SchoolEntryLogRepository schoolEntryLogRepository;

    @Autowired
    MutantRepository mutantRepository;

    @GetMapping
    public ResponseEntity<List<SchoolEntryLog>> getAllLogs() {
        return ResponseEntity.status(HttpStatus.OK).body(schoolEntryLogRepository.findAll());
    }

    @GetMapping("/count-entries")
    public ResponseEntity<Long> getNumberOfMutantsInSchool() {
        return ResponseEntity.status(HttpStatus.OK).body(schoolEntryLogRepository.countByExitDateIsNull());
    }

    @PostMapping("/entry/mutant/{mutantId}")
    public ResponseEntity<Object> registerSchoolEntry(@PathVariable Long mutantId) {
        Optional<Mutant> mutantO = mutantRepository.findById(mutantId);

        if (mutantO.isPresent()) {
            var mutant = mutantO.get();

            if (schoolEntryLogRepository.findByMutantIdAndExitDateIsNull(mutantId) != null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mutant is already in school.");
            }

            return ResponseEntity.status(HttpStatus.OK).body(schoolEntryLogRepository.save(new SchoolEntryLog(mutant, LocalDate.now())));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutant not found.");
    }

    @PostMapping("exit/mutant/{mutantId}")
    public ResponseEntity<Object> registerSchoolExit(@PathVariable Long mutantId) {
        SchoolEntryLog entryLog = schoolEntryLogRepository.findByMutantIdAndExitDateIsNull(mutantId);

        if (entryLog == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mutant is not in school.");
        }

        entryLog.setExitDate(LocalDate.now());
        return ResponseEntity.status(HttpStatus.OK).body(schoolEntryLogRepository.save(entryLog));
    }
}
