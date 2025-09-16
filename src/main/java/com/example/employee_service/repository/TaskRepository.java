package com.example.employee_service.repository;

import com.example.employee_service.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    List<Task> findByEmployee_IdAndStatusIgnoreCase(String employeeId, String status);
}


