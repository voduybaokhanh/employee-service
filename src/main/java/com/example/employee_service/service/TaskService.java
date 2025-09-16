package com.example.employee_service.service;

import com.example.employee_service.dto.TaskDtos;
import com.example.employee_service.dto.TaskSearchParams;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.entity.Task;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    // Dynamic search using Specification with optional filters
    public List<Task> searchTasks(TaskSearchParams params) {
        Specification<Task> spec = Specification.where(null);
        if (params.getEmployeeId() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("employee").get("id"), params.getEmployeeId()));
        }
        if (params.getStatus() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), params.getStatus()));
        }
        if (params.getDueDate() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("dueDate"), params.getDueDate()));
        }
        return taskRepository.findAll(spec);
    }
}


