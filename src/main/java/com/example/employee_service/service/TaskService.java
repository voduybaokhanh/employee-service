package com.example.employee_service.service;

import com.example.employee_service.dto.TaskDtos;
import com.example.employee_service.dto.TaskSearchParams;
import com.example.employee_service.dto.TaskAssignDto;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.entity.Task;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.repository.TaskRepository;
import com.example.employee_service.event.TaskAssignedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final ApplicationEventPublisher eventPublisher;

    // Create a new task for an employee
    public Task createTask(TaskDtos.CreateRequest request) {
        // Business rule: employee must exist
        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElse(null);
        if (employee == null) {
            return null;
        }
        Task task = new Task();
        task.setEmployeeId(request.getEmployeeId());
        task.setTaskName(request.getTaskName());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus() != null ? request.getStatus() : "TO_DO");
        task.setDueDate(request.getDueDate());
        return taskRepository.save(task);
    }

    // Dynamic search using Specification with optional filters
    public List<Task> searchTasks(TaskSearchParams params) {
        List<Specification<Task>> parts = new java.util.ArrayList<>();
        if (params.getEmployeeId() != null) {
            parts.add((root, query, cb) -> cb.equal(root.get("employeeId"), params.getEmployeeId()));
        }
        if (params.getStatus() != null) {
            parts.add((root, query, cb) -> cb.equal(root.get("status"), params.getStatus()));
        }
        if (params.getDueDate() != null) {
            parts.add((root, query, cb) -> cb.equal(root.get("dueDate"), params.getDueDate()));
        }
        Specification<Task> combined = parts.isEmpty() ? (root, query, cb) -> cb.conjunction() : Specification.allOf(parts);
        return taskRepository.findAll(combined);
    }

    // Assign a task to an employee and publish async event
    public Task assignTask(Long taskId, TaskAssignDto request) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) return null;
        Employee assignee = employeeRepository.findById(request.getEmployeeId()).orElse(null);
        if (assignee == null) return null;

        // Update assignment data
        task.setEmployeeId(request.getEmployeeId());
        task.setAssignedBy(request.getAssignedBy());
        Task saved = taskRepository.save(task);

        // Publish event without blocking
        eventPublisher.publishEvent(new TaskAssignedEvent(this, saved, request.getAssignedBy()));
        return saved;
    }
}


