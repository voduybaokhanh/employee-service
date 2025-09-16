package com.example.employee_service.repository;

import com.example.employee_service.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    List<Task> findByEmployee_IdAndStatusIgnoreCase(String employeeId, String status);

    @Query("SELECT t.status, COUNT(t) FROM Task t WHERE t.employee.department = :departmentName GROUP BY t.status")
    List<Object[]> countByStatusInDepartment(@Param("departmentName") String departmentName);
}


