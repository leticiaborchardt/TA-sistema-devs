package com.example.devs.repositories;

import com.example.devs.models.DefeatedEnemiesLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefeatedEnemiesLogRepository  extends JpaRepository<DefeatedEnemiesLog, Long> {
    List<DefeatedEnemiesLog> findAllByMutantId(Long id);
}
