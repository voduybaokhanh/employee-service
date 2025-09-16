package com.example.employee_service.service;

import com.example.employee_service.dto.DepartmentAverageSalaryDto;
import com.example.employee_service.entity.Employee;
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

    // Compute average salary grouped by department name
    public List<DepartmentAverageSalaryDto> getDepartmentAverageSalaries() {
        List<Employee> employees = employeeRepository.findAll();
        Map<String, List<Employee>> byDept = employees.stream()
                .filter(e -> e.getDepartment() != null && e.getSalary() != null)
                .collect(Collectors.groupingBy(Employee::getDepartment));

        List<DepartmentAverageSalaryDto> result = new ArrayList<>();
        for (Map.Entry<String, List<Employee>> entry : byDept.entrySet()) {
            double avg = entry.getValue().stream().collect(Collectors.averagingInt(Employee::getSalary));
            result.add(new DepartmentAverageSalaryDto(entry.getKey(), avg));
        }
        // Ensure departments with no employees still appear with null/0? Keeping only those with employees for now.
        return result;
    }
}


