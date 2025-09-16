package com.example.employee_service.dto;

import com.example.employee_service.entity.Task;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeProfileDto {
    private String id;
    private String fullname;
    private String email;
    private String department;
    private List<Task> ongoingTasks;
}


