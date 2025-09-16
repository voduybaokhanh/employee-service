package com.example.employee_service.controller;

import com.example.employee_service.dto.ApiResponse;
import com.example.employee_service.dto.TaskDtos;
import com.example.employee_service.entity.Task;
import com.example.employee_service.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}


