package com.example.employee_service.service;

import com.example.employee_service.dto.TaskDtos;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.entity.Task;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    // Create a new task for an employee
    public Task createTask(TaskDtos.CreateRequest request) {
        // Business rule: employee must exist
        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElse(null);
        if (employee == null) {
            return null;
        }
        Task task = new Task();
        task.setEmployee(employee);
        task.setTaskName(request.getTaskName());
        task.setDescription(request.getDescription());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }
}


