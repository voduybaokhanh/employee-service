package com.example.employee_service.controller;

import com.example.employee_service.dto.ApiResponse;
import com.example.employee_service.dto.TaskDtos;
import com.example.employee_service.dto.TaskSearchParams;
import com.example.employee_service.dto.TaskAssignDto;
import com.example.employee_service.entity.Task;
import com.example.employee_service.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    // POST /api/tasks: create a new task
    @PostMapping
    public ResponseEntity<ApiResponse<Task>> createTask(@RequestBody TaskDtos.CreateRequest request) {
        // Business rule: employee must exist to assign a task
        Task created = taskService.createTask(request);
        if (created == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.message(false, "Invalid employee_id"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(created));
    }

    // GET /api/tasks/search: dynamic search by optional params using Specification
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Task>>> searchTasks(
            @RequestParam(required = false, name = "employee_id") String employeeId,
            @RequestParam(required = false, name = "status") String status,
            @RequestParam(required = false, name = "due_date") java.time.LocalDate dueDate
    ) {
        TaskSearchParams params = new TaskSearchParams();
        params.setEmployeeId(employeeId);
        params.setStatus(status);
        params.setDueDate(dueDate);
        List<Task> tasks = taskService.searchTasks(params);
        return ResponseEntity.ok(ApiResponse.ok(tasks));
    }

    // PUT /api/tasks/{id}/assign: assign task to employee and publish event
    @PutMapping("/{id}/assign")
    public ResponseEntity<ApiResponse<Task>> assignTask(
            @PathVariable Long id,
            @RequestBody TaskAssignDto request
    ) {
        Task saved = taskService.assignTask(id, request);
        if (saved == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.message(false, "Invalid task or employee"));
        }
        return ResponseEntity.ok(ApiResponse.ok(saved));
    }
}


