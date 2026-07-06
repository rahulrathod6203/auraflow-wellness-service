package com.awp.periodLog.repository;

import com.awp.periodLog.model.PeriodLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodLogRepository extends JpaRepository<PeriodLog, Long> {
}
