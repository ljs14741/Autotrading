package com.bitcoin.autotrading.commandLine.domain.repository;

import com.bitcoin.autotrading.commandLine.domain.ProgramLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramLogRepository extends JpaRepository<ProgramLog, Long> {
}
