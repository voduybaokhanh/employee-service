package com.example.employee_service.controller;

import com.example.employee_service.dto.ApiResponse;
import com.example.employee_service.dto.EmployeeDtos;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    // GET /api/employees/{id}: return employee details by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployee(@PathVariable String id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return ResponseEntity.status(404).body(ApiResponse.message(false, "Employee not found"));
        }
        return ResponseEntity.ok(ApiResponse.ok(employee));
    }

    // PUT /api/employees/{id}: update fullname, position, salary
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(
            @PathVariable String id,
            @RequestBody EmployeeDtos.UpdateRequest request
    ) {
        // Business logic: only specific fields allowed to change
        Employee updated = employeeService.updateEmployee(id, request);
        if (updated == null) {
            return ResponseEntity.status(404).body(ApiResponse.message(false, "Employee not found"));
        }
        return ResponseEntity.ok(ApiResponse.ok(updated));
    }
}


