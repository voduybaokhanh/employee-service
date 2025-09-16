package com.example.employee_service.service;

import com.example.employee_service.dto.EmployeeDtos;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

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
}


