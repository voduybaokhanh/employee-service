package com.example.employee_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskSearchParams {
    private String employeeId;
    private String status;
    private LocalDate dueDate;
}


