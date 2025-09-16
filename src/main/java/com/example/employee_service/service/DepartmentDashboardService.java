package com.example.employee_service.service;

import com.example.employee_service.dto.DepartmentDashboardDto;
import com.example.employee_service.entity.Department;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.DepartmentRepository;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentDashboardService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;

    public DepartmentDashboardDto getDashboard(Long departmentId) {
        Department dept = departmentRepository.findById(departmentId).orElse(null);
        if (dept == null) return null;
        String deptName = dept.getName();

        // Employees in department
        List<Employee> employees = employeeRepository.findByDepartmentName(deptName);
        long totalEmployees = employees.size();
        double avgSalary = employees.stream()
                .filter(e -> e.getSalary() != null)
                .mapToInt(Employee::getSalary)
                .average()
                .orElse(0.0);

        // Tasks by status for this department
        List<Object[]> raw = taskRepository.countByStatusInDepartment(deptName);
        Map<String, Long> byStatus = new HashMap<>();
        for (Object[] row : raw) {
            String status = (String) row[0];
            Long count = (Long) row[1];
            byStatus.put(status, count);
        }

        // New employees in last 30 days
        LocalDateTime since = LocalDateTime.now().minusDays(30);
        List<Employee> newEmployees = employeeRepository.findNewEmployeesSince(deptName, since);

        DepartmentDashboardDto dto = new DepartmentDashboardDto();
        dto.setDepartmentId(departmentId);
        dto.setDepartmentName(deptName);
        dto.setTotalEmployees(totalEmployees);
        dto.setAverageSalary(avgSalary);
        dto.setTasksByStatus(byStatus);
        dto.setNewEmployeesLast30Days(newEmployees);
        return dto;
    }
}


