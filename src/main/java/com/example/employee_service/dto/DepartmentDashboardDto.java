package com.example.employee_service.dto;

import com.example.employee_service.entity.Employee;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DepartmentDashboardDto {
    private Long departmentId;
    private String departmentName;
    private Long totalEmployees;
    private Double averageSalary;
    private Map<String, Long> tasksByStatus;
    private List<Employee> newEmployeesLast30Days;
}


