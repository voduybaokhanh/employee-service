package com.example.employee_service.dto;

import lombok.Data;

@Data
public class TaskAssignDto {
    private String employeeId; // assignee
    private String assignedBy; // who assigned
}


