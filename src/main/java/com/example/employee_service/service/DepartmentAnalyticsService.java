package com.example.employee_service.service;

import com.example.employee_service.dto.DepartmentAverageSalaryDto;
import com.example.employee_service.entity.Department;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.DepartmentRepository;
import com.example.employee_service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentAnalyticsService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    // Compute average salary grouped by department name
    public List<DepartmentAverageSalaryDto> getDepartmentAverageSalaries() {
        List<Employee> employees = employeeRepository.findAll();
        Map<String, List<Employee>> byDept = employees.stream()
                .filter(e -> e.getDepartment() != null && e.getSalary() != null)
                .collect(Collectors.groupingBy(Employee::getDepartment));

        List<DepartmentAverageSalaryDto> result = new ArrayList<>();
        for (Map.Entry<String, List<Employee>> entry : byDept.entrySet()) {
            String departmentId = entry.getKey();
            Department dept = departmentRepository.findById(departmentId).orElse(null);
            String departmentName = dept != null ? dept.getName() : departmentId;
            double avg = entry.getValue().stream().collect(Collectors.averagingInt(Employee::getSalary));
            result.add(new DepartmentAverageSalaryDto(departmentName, avg));
        }
        return result;
    }
}


