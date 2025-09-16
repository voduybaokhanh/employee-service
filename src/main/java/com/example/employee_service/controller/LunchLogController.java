package com.example.employee_service.controller;

import com.example.employee_service.dto.ApiResponse;
import com.example.employee_service.dto.LunchLogDtos;
import com.example.employee_service.entity.LunchLog;
import com.example.employee_service.service.LunchLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lunch-logs")
@RequiredArgsConstructor
public class LunchLogController {
    private final LunchLogService lunchLogService;

    // POST /api/lunch-logs/bulk: save multiple lunch logs in one request
    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<LunchLog>>> bulkCreate(@RequestBody LunchLogDtos.BulkCreateRequest request) {
        List<LunchLog> saved = lunchLogService.bulkCreate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(saved));
    }
}


