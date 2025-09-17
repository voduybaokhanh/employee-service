package com.example.employee_service.repository;

import com.example.employee_service.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    List<Task> findByEmployeeIdAndStatusIgnoreCase(String employeeId, String status);

    @Query("SELECT t.status, COUNT(t) FROM Task t JOIN Employee e ON t.employeeId = e.id WHERE e.department = :departmentId GROUP BY t.status")
    List<Object[]> countByStatusInDepartment(@Param("departmentId") String departmentId);
}


