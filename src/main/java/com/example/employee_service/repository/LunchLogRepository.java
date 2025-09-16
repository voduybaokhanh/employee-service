package com.example.employee_service.repository;

import com.example.employee_service.entity.LunchLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchLogRepository extends JpaRepository<LunchLog, Long> {
}


