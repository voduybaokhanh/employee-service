package com.example.employee_service.repository;

import com.example.employee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("SELECT e FROM Employee e WHERE e.department = :departmentName")
    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);

    @Query("SELECT e FROM Employee e WHERE e.department = :departmentName AND e.createdAt >= :since")
    List<Employee> findNewEmployeesSince(@Param("departmentName") String departmentName, @Param("since") LocalDateTime since);
}
