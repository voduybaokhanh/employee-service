package com.example.employee_service.repository;

import com.example.employee_service.entity.DepartmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentHistoryRepository extends JpaRepository<DepartmentHistory, Long> {
}


