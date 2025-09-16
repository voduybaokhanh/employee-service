package com.example.employee_service.controller;

import com.example.employee_service.dto.ApiResponse;
import com.example.employee_service.dto.DepartmentDtos;
import com.example.employee_service.entity.Department;
import com.example.employee_service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    // GET /api/departments: list all departments
    @GetMapping
    public ResponseEntity<ApiResponse<List<Department>>> listDepartments() {
        List<Department> departments = departmentService.getAll();
        return ResponseEntity.ok(ApiResponse.ok(departments));
    }

    // POST /api/departments: create department (idempotent by name)
    @PostMapping
    public ResponseEntity<ApiResponse<Department>> createDepartment(@RequestBody DepartmentDtos.CreateRequest request) {
        // Business rule: avoid duplicates by name (case-insensitive)
        Department department = departmentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(department));
    }
}


