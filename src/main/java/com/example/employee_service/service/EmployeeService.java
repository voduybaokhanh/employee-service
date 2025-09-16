package com.example.employee_service.service;

import com.example.employee_service.dto.EmployeeDepartmentChangeDto;
import com.example.employee_service.dto.EmployeeDtos;
import com.example.employee_service.entity.Department;
import com.example.employee_service.entity.DepartmentHistory;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.DepartmentHistoryRepository;
import com.example.employee_service.repository.DepartmentRepository;
import com.example.employee_service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final DepartmentHistoryRepository departmentHistoryRepository;

    // Find employee by id or return null when not found
    public Employee findById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Update mutable fields and persist
    public Employee updateEmployee(String id, EmployeeDtos.UpdateRequest request) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return null;
        }
        // Business rule: allow updating fullname, position, salary only
        if (request.getFullname() != null) employee.setFullname(request.getFullname());
        if (request.getPosition() != null) employee.setPosition(request.getPosition());
        if (request.getSalary() != null) employee.setSalary(request.getSalary());
        employee.setUpdatedAt(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    // Transactional department change: update employee.department and add history
    @Transactional
    public boolean changeDepartment(String employeeId, EmployeeDepartmentChangeDto request) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) return false;
        Department department = departmentRepository.findById(request.getDepartmentId()).orElse(null);
        if (department == null) return false;

        // Update current department on employee
        employee.setDepartment(department.getName());
        employee.setUpdatedAt(LocalDateTime.now());
        employeeRepository.save(employee);

        // Insert history record
        DepartmentHistory history = new DepartmentHistory();
        history.setEmployee(employee);
        history.setDepartment(department);
        history.setChangedAt(LocalDateTime.now());
        departmentHistoryRepository.save(history);
        return true;
    }
}


