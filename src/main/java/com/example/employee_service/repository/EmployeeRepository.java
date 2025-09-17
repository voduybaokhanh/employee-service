package com.example.employee_service.repository;

import com.example.employee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("SELECT e FROM Employee e WHERE e.department = :departmentId")
    List<Employee> findByDepartmentName(@Param("departmentId") String departmentId);

    @Query("SELECT e FROM Employee e WHERE e.department = :departmentId AND e.createdAt >= :since")
    List<Employee> findNewEmployeesSince(@Param("departmentId") String departmentId, @Param("since") LocalDateTime since);
}
