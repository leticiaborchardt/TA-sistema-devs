package com.example.devs.repositories;

import com.example.devs.models.SchoolEntryLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolEntryLogRepository extends JpaRepository<SchoolEntryLog, Long> {
    SchoolEntryLog findByMutantIdAndExitDateIsNull(long mutantId);

    List<SchoolEntryLog> findAllByExitDateIsNull();

    long countByExitDateIsNull();
}
