package com.example.employee_service.service;

import com.example.employee_service.dto.EmployeeProfileDto;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.entity.Task;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeProfileService {
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;

    // Aggregate employee info with ongoing tasks
    public EmployeeProfileDto getProfile(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) return null;
        List<Task> ongoing = taskRepository.findByEmployee_IdAndStatusIgnoreCase(employeeId, "ONGOING");
        EmployeeProfileDto dto = new EmployeeProfileDto();
        dto.setId(employee.getId());
        dto.setFullname(employee.getFullname());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setOngoingTasks(ongoing);
        return dto;
    }
}


