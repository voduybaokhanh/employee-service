package com.example.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentAverageSalaryDto {
    private String departmentName;
    private Double averageSalary;
}


