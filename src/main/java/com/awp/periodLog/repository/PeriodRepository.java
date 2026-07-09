package com.awp.periodLog.repository;

import com.awp.auth.model.User;
import com.awp.periodLog.model.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {


    List<Period> findPeriodByUser_Id(Long userId);
}
